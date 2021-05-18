package com.pos.lms.kms_pt_pos_indonesia.ui.digilab.detaildigilab.comment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.DigilabCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.WahanaCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.digilab.digilabcomment.DigilabCommentUsecase
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.wahana.wahanacomment.WahanaCommentUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class DigilabCommentViewModel @ViewModelInject constructor(
    private val digilabCommentUsecase: DigilabCommentUsecase
) : ViewModel() {

    fun getDigilabComment(order: String, knowledgeDigilab: Int) =
        digilabCommentUsecase.getDigilabComment(order,knowledgeDigilab).asLiveData()

    fun createDigilabComment(createDigilabComment: DigilabCommentCreate) =
        digilabCommentUsecase.createDigilabComment(createDigilabComment).asLiveData()

}