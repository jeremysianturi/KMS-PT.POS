package com.pos.lms.kms_pt_pos_indonesia.domain.usecase.digilab.digilabcomment

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.repository.DigilabCommentRepository
import com.pos.lms.kms_pt_pos_indonesia.data.repository.WahanaCommentRepository
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.DigilabCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.WahanaCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.domain.model.DigilabComment
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Submit
import com.pos.lms.kms_pt_pos_indonesia.domain.model.WahanaComment
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.wahana.wahanacomment.WahanaCommentUsecase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DigilabCommentInteractor @Inject constructor(private val digilabCommentRepository : DigilabCommentRepository) :
    DigilabCommentUsecase {

    override fun getDigilabComment(order: String, knowledgeDigilab: Int): Flow<Resource<List<DigilabComment>>> =
        digilabCommentRepository.getDigilabComment(order,knowledgeDigilab)

    override fun createDigilabComment(digilabCommentCreate: DigilabCommentCreate): Flow<Resource<Submit>> =
        digilabCommentRepository.createDigilabComment(digilabCommentCreate)

}