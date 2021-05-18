package com.pos.lms.kms_pt_pos_indonesia.data.repository

import com.pos.lms.kms_pt_pos_indonesia.data.NetworkBoundResourceWithDeleteLocalData
import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.LocalDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.RemoteDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.network.ApiResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.multimedia.MultimediaResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Multimedia
import com.pos.lms.kms_pt_pos_indonesia.domain.repository.IMultimediaRepository
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperMultimedia
import com.pos.lms.kms_pt_pos_indonesia.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MultimediaRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMultimediaRepository  {

    override fun getMultimedia(type: String): Flow<Resource<List<Multimedia>>>  =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<Multimedia>, List<MultimediaResponse>>() {

            override fun loadFromDB(): Flow<List<Multimedia>> {
                return localDataSource.getMultimedia().map {
                    DataMapperMultimedia.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<Multimedia>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<MultimediaResponse>>> =
                remoteDataSource.getMultimedia(type)

            override suspend fun saveCallResult(data: List<MultimediaResponse>) {
                val list = DataMapperMultimedia.mapResponsetoEntities(data)
                localDataSource.insertMultimedia(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteMultimedia()
            }

        }.asFlow()

//    override fun getSearchMultimedia(search: String): Flow<List<Multimedia>> {
//        return localDataSource.getSearchMultimedia(search).map {
//            DataMapperMultimedia.mapEntitiestoDomain(it)
//        }
//    }


}