package com.pos.lms.kms_pt_pos_indonesia.data.repository

import com.pos.lms.kms_pt_pos_indonesia.data.NetworkBoundResourceWithDeleteLocalData
import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.LocalDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.RemoteDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.network.ApiResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.digilab.DigilabResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Digilab
import com.pos.lms.kms_pt_pos_indonesia.domain.repository.IDigilabRepository
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperDigilab
import com.pos.lms.kms_pt_pos_indonesia.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DigilabRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IDigilabRepository  {

    override fun getDigilab(type: String): Flow<Resource<List<Digilab>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<Digilab>, List<DigilabResponse>>() {

            override fun loadFromDB(): Flow<List<Digilab>> {
                return localDataSource.getDigilab().map {
                    DataMapperDigilab.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<Digilab>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<DigilabResponse>>> =
                remoteDataSource.getDigilab(type)

            override suspend fun saveCallResult(data: List<DigilabResponse>) {
                val list = DataMapperDigilab.mapResponsetoEntities(data)
                localDataSource.insertDigilab(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteDigilab()
            }

        }.asFlow()

//    override fun getSearchDigilab(search: String): Flow<List<Digilab>> {
//        return localDataSource.getSearchDigilab(search).map {
//            DataMapperDigilab.mapEntitiestoDomain(it)
//        }
//    }

}