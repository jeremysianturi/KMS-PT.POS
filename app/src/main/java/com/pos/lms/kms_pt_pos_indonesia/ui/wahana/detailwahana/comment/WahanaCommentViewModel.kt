package com.pos.lms.kms_pt_pos_indonesia.ui.wahana.detailwahana.comment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.WahanaCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.wahana.wahanacomment.WahanaCommentUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class WahanaCommentViewModel @ViewModelInject constructor(
    private val wahanaCommentUsecase: WahanaCommentUsecase,
    ) : ViewModel(){

    fun getWahanaComment(order: String, knowledgeWahana: Int) =
        wahanaCommentUsecase.getWahanaComment(order,knowledgeWahana).asLiveData()

    fun createWahanaComment(createWahanaComment: WahanaCommentCreate) =
        wahanaCommentUsecase.createWahanaComment(createWahanaComment).asLiveData()

}