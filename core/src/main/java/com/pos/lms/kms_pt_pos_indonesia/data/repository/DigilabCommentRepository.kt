package com.pos.lms.kms_pt_pos_indonesia.data.repository

import com.pos.lms.kms_pt_pos_indonesia.data.NetworkBoundResource
import com.pos.lms.kms_pt_pos_indonesia.data.NetworkBoundResourceWithDeleteLocalData
import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.LocalDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.RemoteDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.network.ApiResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.DigilabCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.WahanaCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.SubmitResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.digilab.digilabcomment.DigilabCommentResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.wahanacomment.WahanaCommentResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.DigilabComment
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Submit
import com.pos.lms.kms_pt_pos_indonesia.domain.model.WahanaComment
import com.pos.lms.kms_pt_pos_indonesia.domain.repository.IDigilabCommentRespository
import com.pos.lms.kms_pt_pos_indonesia.domain.repository.IWahanaCommentRepository
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperDigilabComment
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperSubmit
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperWahanaComment
import com.pos.lms.kms_pt_pos_indonesia.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DigilabCommentRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IDigilabCommentRespository {

    override fun getDigilabComment(order: String, knowledgeDigilab: Int): Flow<Resource<List<DigilabComment>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<DigilabComment>, List<DigilabCommentResponse>>() {

            override fun loadFromDB(): Flow<List<DigilabComment>> {
                return localDataSource.getDigilabComment().map {
                    DataMapperDigilabComment.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<DigilabComment>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<DigilabCommentResponse>>> =
                remoteDataSource.getDigilabComment(order,knowledgeDigilab)

            override suspend fun saveCallResult(data: List<DigilabCommentResponse>) {
                val list = DataMapperDigilabComment.mapResponsetoEntities(data)
                localDataSource.insertDigilabComment(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteDigilabComment()
            }

        }.asFlow()

    override fun createDigilabComment(digilabCommentCreate: DigilabCommentCreate): Flow<Resource<Submit>> =
        object : NetworkBoundResource<Submit, SubmitResponse>() {

            override fun loadFromDB(): Flow<Submit> {
                return localDataSource.getSubmitResponse().map {
                    DataMapperSubmit.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: Submit?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<SubmitResponse>> =
                remoteDataSource.createDigilabComment(digilabCommentCreate)

            override suspend fun saveCallResult(data: SubmitResponse) {
                val list = DataMapperSubmit.mapResponsetoEntities(data)
                localDataSource.insertSubmitResponse(list)
            }

        }.asFlow()

}