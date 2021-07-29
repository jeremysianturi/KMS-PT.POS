package com.pos.lms.kms_pt_pos_indonesia.domain.repository

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.DigilabCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.MultimediaCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.domain.model.DigilabComment
import com.pos.lms.kms_pt_pos_indonesia.domain.model.MultimediaComment
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Submit
import kotlinx.coroutines.flow.Flow

interface IMultimediaCommentRepository {

    fun getMultimediaComment(order: String, knowledgeMultimedia: Int, beginDate : String, endDate : String) : Flow<Resource<List<MultimediaComment>>>

    fun createMultimediaComment(multimediaCommentCreate: MultimediaCommentCreate) : Flow<Resource<Submit>>

}