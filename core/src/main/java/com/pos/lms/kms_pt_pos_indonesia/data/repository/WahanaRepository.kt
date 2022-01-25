package com.pos.lms.kms_pt_pos_indonesia.data.repository

import com.pos.lms.kms_pt_pos_indonesia.data.NetworkBoundResourceWithDeleteLocalData
import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.LocalDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.RemoteDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.network.ApiResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.WahanaCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.SubmitResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.WahanaResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Submit
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana
import com.pos.lms.kms_pt_pos_indonesia.domain.repository.IWahanaRepository
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperSubmit
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperWahana
import com.pos.lms.kms_pt_pos_indonesia.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WahanaRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExexcutors: AppExecutors
) : IWahanaRepository {

    override fun getWahana(type: String): Flow<Resource<List<Wahana>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<Wahana>, List<WahanaResponse>>() {

            override fun loadFromDB(): Flow<List<Wahana>> {
                return localDataSource.getWahana().map {
                    DataMapperWahana.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<Wahana>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<WahanaResponse>>> =
                remoteDataSource.getWahana(type)

            override suspend fun saveCallResult(data: List<WahanaResponse>) {
                val list = DataMapperWahana.mapResponsetoEntities(data)
                localDataSource.insertWahana(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteWahana()
            }

        }.asFlow()

//    override fun getSearchWahana(search: String): Flow<List<Wahana>> {
//        return localDataSource.getSearchWahana(search).map {
//            DataMapperWahana.mapEntitiestoDomain(it)
//        }
//    }

}