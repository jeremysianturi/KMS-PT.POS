package com.pos.lms.kms_pt_pos_indonesia.data.repository

import com.pos.lms.kms_pt_pos_indonesia.data.NetworkBoundResourceWithDeleteLocalData
import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.LocalDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.RemoteDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.network.ApiResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.digilab.searchdigilab.SearchDigilabResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.searchwahana.SearchWahanaResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Digilab
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana
import com.pos.lms.kms_pt_pos_indonesia.domain.repository.ISearchDigilabRepository
import com.pos.lms.kms_pt_pos_indonesia.domain.repository.ISearchWahanaRepository
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperSearchDigilab
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperSearchWahana
import com.pos.lms.kms_pt_pos_indonesia.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchDigilabRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ISearchDigilabRepository {

    override fun getSearchDigilab(type: String, search: String, category: String): Flow<Resource<List<Digilab>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<Digilab>, List<SearchDigilabResponse>>() {

            override fun loadFromDB(): Flow<List<Digilab>> {
                return localDataSource.getSearchDigilab().map {
                    DataMapperSearchDigilab.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<Digilab>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<SearchDigilabResponse>>> =
                remoteDataSource.getSearchDigilab(type,search,category)

            override suspend fun saveCallResult(data: List<SearchDigilabResponse>) {
                val list = DataMapperSearchDigilab.mapResponsetoEntities(data)
                localDataSource.insertSearchDigilab(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteSearchDigilab()
            }

        }.asFlow()

}