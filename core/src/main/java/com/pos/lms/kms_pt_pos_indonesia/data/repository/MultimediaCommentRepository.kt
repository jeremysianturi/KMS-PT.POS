package com.pos.lms.kms_pt_pos_indonesia.data.repository

import com.pos.lms.kms_pt_pos_indonesia.data.NetworkBoundResource
import com.pos.lms.kms_pt_pos_indonesia.data.NetworkBoundResourceWithDeleteLocalData
import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.LocalDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.RemoteDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.network.ApiResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.DigilabCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.MultimediaCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.SubmitResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.digilab.digilabcomment.DigilabCommentResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.multimedia.multimediacomment.MultimediaCommentResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.DigilabComment
import com.pos.lms.kms_pt_pos_indonesia.domain.model.MultimediaComment
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Submit
import com.pos.lms.kms_pt_pos_indonesia.domain.repository.IDigilabCommentRespository
import com.pos.lms.kms_pt_pos_indonesia.domain.repository.IMultimediaCommentRepository
import com.pos.lms.kms_pt_pos_indonesia.domain.repository.IMultimediaRepository
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperDigilabComment
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperMultimediaComment
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperSubmit
import com.pos.lms.kms_pt_pos_indonesia.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MultimediaCommentRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMultimediaCommentRepository {

    override fun getMultimediaComment(order: String, knowledgeMultimedia: Int): Flow<Resource<List<MultimediaComment>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<MultimediaComment>, List<MultimediaCommentResponse>>() {

            override fun loadFromDB(): Flow<List<MultimediaComment>> {
                return localDataSource.getMultimediaComment().map {
                    DataMapperMultimediaComment.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<MultimediaComment>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<MultimediaCommentResponse>>> =
                remoteDataSource.getMultimediaComment(order,knowledgeMultimedia)

            override suspend fun saveCallResult(data: List<MultimediaCommentResponse>) {
                val list = DataMapperMultimediaComment.mapResponsetoEntities(data)
                localDataSource.insertMultimediaComment(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteMultimediaComment()
            }

        }.asFlow()

    override fun createMultimediaComment(multimediaCommentCreate: MultimediaCommentCreate): Flow<Resource<Submit>> =
        object : NetworkBoundResource<Submit, SubmitResponse>() {

            override fun loadFromDB(): Flow<Submit> {
                return localDataSource.getSubmitResponse().map {
                    DataMapperSubmit.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: Submit?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<SubmitResponse>> =
                remoteDataSource.createMultimediaComment(multimediaCommentCreate)

            override suspend fun saveCallResult(data: SubmitResponse) {
                val list = DataMapperSubmit.mapResponsetoEntities(data)
                localDataSource.insertSubmitResponse(list)
            }

        }.asFlow()

}