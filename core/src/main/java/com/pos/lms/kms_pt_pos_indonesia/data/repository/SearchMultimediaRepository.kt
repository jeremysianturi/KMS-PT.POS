package com.pos.lms.kms_pt_pos_indonesia.data.repository

import com.pos.lms.kms_pt_pos_indonesia.data.NetworkBoundResourceWithDeleteLocalData
import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.LocalDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.RemoteDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.network.ApiResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.multimedia.searchmultimedia.SearchMultimediaResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.searchwahana.SearchWahanaResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Multimedia
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana
import com.pos.lms.kms_pt_pos_indonesia.domain.repository.IMultimediaRepository
import com.pos.lms.kms_pt_pos_indonesia.domain.repository.ISearchMultimediaRepository
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperSearchMultimedia
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperSearchWahana
import com.pos.lms.kms_pt_pos_indonesia.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchMultimediaRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ISearchMultimediaRepository {

    override fun getSearchMultimedia(type: String, search: String): Flow<Resource<List<Multimedia>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<Multimedia>, List<SearchMultimediaResponse>>() {

            override fun loadFromDB(): Flow<List<Multimedia>> {
                return localDataSource.getSearchMultimedia().map {
                    DataMapperSearchMultimedia.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<Multimedia>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<SearchMultimediaResponse>>> =
                remoteDataSource.getSearchMultimedia(type,search)

            override suspend fun saveCallResult(data: List<SearchMultimediaResponse>) {
                val list = DataMapperSearchMultimedia.mapResponsetoEntities(data)
                localDataSource.insertSearchMultimedia(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteSearchMultimedia()
            }

        }.asFlow()

}