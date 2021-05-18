package com.pos.lms.kms_pt_pos_indonesia.ui.inbox

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.inbox.InboxUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

@ExperimentalCoroutinesApi
class InboxViewModel @ViewModelInject constructor(private val inboxUsecase: InboxUsecase) : ViewModel() {

    fun getInbox(type: String) = inboxUsecase.getInbox(type).asLiveData()

}