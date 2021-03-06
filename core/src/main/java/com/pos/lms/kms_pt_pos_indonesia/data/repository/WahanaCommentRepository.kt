package com.pos.lms.kms_pt_pos_indonesia.data.repository

import com.pos.lms.kms_pt_pos_indonesia.data.NetworkBoundResource
import com.pos.lms.kms_pt_pos_indonesia.data.NetworkBoundResourceWithDeleteLocalData
import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.LocalDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.RemoteDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.network.ApiResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.WahanaCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.SubmitResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.WahanaResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.wahanacomment.WahanaCommentResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Submit
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana
import com.pos.lms.kms_pt_pos_indonesia.domain.model.WahanaComment
import com.pos.lms.kms_pt_pos_indonesia.domain.repository.IWahanaCommentRepository
import com.pos.lms.kms_pt_pos_indonesia.domain.repository.IWahanaRepository
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperSubmit
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperWahana
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperWahanaComment
import com.pos.lms.kms_pt_pos_indonesia.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WahanaCommentRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IWahanaCommentRepository {

    override fun getWahanaComment(order: String, knowledgeWahana: Int, beginDate : String, endDate : String): Flow<Resource<List<WahanaComment>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<WahanaComment>, List<WahanaCommentResponse>>() {

            override fun loadFromDB(): Flow<List<WahanaComment>> {
                return localDataSource.getWahanaComment().map {
                    DataMapperWahanaComment.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<WahanaComment>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<WahanaCommentResponse>>> =
                remoteDataSource.getWahanaComment(order,knowledgeWahana,beginDate,endDate)

            override suspend fun saveCallResult(data: List<WahanaCommentResponse>) {
                val list = DataMapperWahanaComment.mapResponsetoEntities(data)
                localDataSource.insertWahanaComment(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteWahanaComment()
            }

        }.asFlow()

    override fun createWahanaComment(wahanaCommentCreate: WahanaCommentCreate): Flow<Resource<Submit>> =
        object : NetworkBoundResource<Submit, SubmitResponse>() {

            override fun loadFromDB(): Flow<Submit> {
                return localDataSource.getSubmitResponse().map {
                    DataMapperSubmit.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: Submit?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<SubmitResponse>> =
                remoteDataSource.createWahanaComment(wahanaCommentCreate)

            override suspend fun saveCallResult(data: SubmitResponse) {
                val list = DataMapperSubmit.mapResponsetoEntities(data)
                localDataSource.insertSubmitResponse(list)
            }

        }.asFlow()

}