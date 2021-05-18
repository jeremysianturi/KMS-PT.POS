package com.pos.lms.kms_pt_pos_indonesia.domain.usecase.wahana.wahanacomment

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.WahanaCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Submit
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana
import com.pos.lms.kms_pt_pos_indonesia.domain.model.WahanaComment
import kotlinx.coroutines.flow.Flow

interface WahanaCommentUsecase {

    fun getWahanaComment(order: String, knowledgeWahana: Int): Flow<Resource<List<WahanaComment>>>

    fun createWahanaComment(wahanaCommentCreate: WahanaCommentCreate) : Flow<Resource<Submit>>


}