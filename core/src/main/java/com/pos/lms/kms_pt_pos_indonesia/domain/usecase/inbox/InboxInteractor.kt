package com.pos.lms.kms_pt_pos_indonesia.domain.usecase.inbox

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.repository.InboxRepository
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Inbox
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InboxInteractor @Inject constructor(private val inboxRepository : InboxRepository) : InboxUsecase {

    override fun getInbox(type: String): Flow<Resource<List<Inbox>>> = inboxRepository.getInbox()

}