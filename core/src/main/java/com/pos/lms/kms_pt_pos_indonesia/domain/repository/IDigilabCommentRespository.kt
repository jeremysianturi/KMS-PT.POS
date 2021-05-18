package com.pos.lms.kms_pt_pos_indonesia.domain.repository

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.DigilabCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.WahanaCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.domain.model.DigilabComment
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Submit
import com.pos.lms.kms_pt_pos_indonesia.domain.model.WahanaComment
import kotlinx.coroutines.flow.Flow

interface IDigilabCommentRespository {

    fun getDigilabComment(order: String, knowledgeDigilab: Int) : Flow<Resource<List<DigilabComment>>>

    fun createDigilabComment(digilabCommentCreate: DigilabCommentCreate) : Flow<Resource<Submit>>

}