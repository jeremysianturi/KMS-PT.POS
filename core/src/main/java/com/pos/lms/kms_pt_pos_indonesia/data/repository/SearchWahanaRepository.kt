package com.pos.lms.kms_pt_pos_indonesia.data.repository

import com.pos.lms.kms_pt_pos_indonesia.data.NetworkBoundResourceWithDeleteLocalData
import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.LocalDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.RemoteDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.network.ApiResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.searchwahana.SearchWahanaResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana
import com.pos.lms.kms_pt_pos_indonesia.domain.repository.ISearchWahanaRepository
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperSearchWahana
import com.pos.lms.kms_pt_pos_indonesia.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchWahanaRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ISearchWahanaRepository {

    override fun getSearchWahana(type: String, search: String): Flow<Resource<List<Wahana>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<Wahana>, List<SearchWahanaResponse>>() {

            override fun loadFromDB(): Flow<List<Wahana>> {
                return localDataSource.getSearchWahana().map {
                    DataMapperSearchWahana.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<Wahana>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<SearchWahanaResponse>>> =
                remoteDataSource.getSearchWahana(type,search)

            override suspend fun saveCallResult(data: List<SearchWahanaResponse>) {
                val list = DataMapperSearchWahana.mapResponsetoEntities(data)
                localDataSource.insertSearchWahana(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteSearchWahana()
            }

        }.asFlow()

}