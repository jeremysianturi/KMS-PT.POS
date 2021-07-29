package com.pos.lms.kms_pt_pos_indonesia.ui.multimedia.detailmultimedia.detailmultimedia

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.DigilabCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.MultimediaCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.digilab.digilabcomment.DigilabCommentUsecase
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.multimedia.multimediacomment.MultimediaCommentUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MultimediaCommentViewModel @ViewModelInject constructor(
    private val multimediaCommentUsecase: MultimediaCommentUsecase
) : ViewModel() {

    fun getMultimediaComment(order: String, knowledgeMultimedia: Int, beginDate : String, endDate : String) =
        multimediaCommentUsecase.getMultimediaComment(order,knowledgeMultimedia,beginDate,endDate).asLiveData()

    fun createMultimediaComment(createMultimediaComment: MultimediaCommentCreate) =
        multimediaCommentUsecase.createMultimediaComment(createMultimediaComment).asLiveData()

}