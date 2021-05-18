package com.pos.lms.kms_pt_pos_indonesia.data.repository

import com.pos.lms.kms_pt_pos_indonesia.data.NetworkBoundResourceWithDeleteLocalData
import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.LocalDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.RemoteDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.network.ApiResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.WahanaResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.wahanaviewers.WahanaViewersResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana
import com.pos.lms.kms_pt_pos_indonesia.domain.model.WahanaViewers
import com.pos.lms.kms_pt_pos_indonesia.domain.repository.IWahanaViewersRepository
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperWahana
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperWahanaViewers
import com.pos.lms.kms_pt_pos_indonesia.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WahanaViewersRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IWahanaViewersRepository {

    override fun getWahanaViewers(idKnowledge: String): Flow<Resource<List<WahanaViewers>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<WahanaViewers>, List<WahanaViewersResponse>>() {

            override fun loadFromDB(): Flow<List<WahanaViewers>> {
                return localDataSource.getWahanaViewers().map {
                    DataMapperWahanaViewers.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<WahanaViewers>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<WahanaViewersResponse>>> =
                remoteDataSource.getWahanaViewers(idKnowledge)

            override suspend fun saveCallResult(data: List<WahanaViewersResponse>) {
                val list = DataMapperWahanaViewers.mapResponsetoEntities(data)
                localDataSource.insertWahanaViewers(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteWahanaViewers()
            }

        }.asFlow()

}