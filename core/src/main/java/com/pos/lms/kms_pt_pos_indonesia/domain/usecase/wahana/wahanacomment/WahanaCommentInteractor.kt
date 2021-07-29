package com.pos.lms.kms_pt_pos_indonesia.domain.usecase.wahana.wahanacomment

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.repository.WahanaCommentRepository
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.WahanaCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Submit
import com.pos.lms.kms_pt_pos_indonesia.domain.model.WahanaComment
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WahanaCommentInteractor @Inject constructor(private val wahanaCommentRepository : WahanaCommentRepository) : WahanaCommentUsecase {

    override fun getWahanaComment(order: String, knowledgeWahana: Int, beginDate : String, endDate : String): Flow<Resource<List<WahanaComment>>> =
        wahanaCommentRepository.getWahanaComment(order,knowledgeWahana,beginDate,endDate)

    override fun createWahanaComment(wahanaCommentCreate: WahanaCommentCreate): Flow<Resource<Submit>> =
        wahanaCommentRepository.createWahanaComment(wahanaCommentCreate)


}