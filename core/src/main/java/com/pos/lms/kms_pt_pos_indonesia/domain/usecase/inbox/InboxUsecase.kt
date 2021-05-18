package com.pos.lms.kms_pt_pos_indonesia.domain.usecase.inbox

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Inbox
import kotlinx.coroutines.flow.Flow

interface InboxUsecase {

    fun getInbox(type: String): Flow<Resource<List<Inbox>>>

}