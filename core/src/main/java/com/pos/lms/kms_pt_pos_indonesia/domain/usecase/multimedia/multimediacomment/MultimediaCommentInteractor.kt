package com.pos.lms.kms_pt_pos_indonesia.domain.usecase.multimedia.multimediacomment

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.repository.DigilabCommentRepository
import com.pos.lms.kms_pt_pos_indonesia.data.repository.MultimediaCommentRepository
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.DigilabCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.MultimediaCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.domain.model.DigilabComment
import com.pos.lms.kms_pt_pos_indonesia.domain.model.MultimediaComment
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Submit
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.digilab.digilabcomment.DigilabCommentUsecase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MultimediaCommentInteractor @Inject constructor(private val multimediaCommentRepository : MultimediaCommentRepository) :
    MultimediaCommentUsecase {

    override fun getMultimediaComment(order: String, knowledgeMultimedia: Int): Flow<Resource<List<MultimediaComment>>> =
        multimediaCommentRepository.getMultimediaComment(order,knowledgeMultimedia)

    override fun createMultimediaComment(multimediaCommentCreate: MultimediaCommentCreate): Flow<Resource<Submit>> =
        multimediaCommentRepository.createMultimediaComment(multimediaCommentCreate)

}