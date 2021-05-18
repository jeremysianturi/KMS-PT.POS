package com.pos.lms.kms_pt_pos_indonesia.domain.repository

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Inbox
import kotlinx.coroutines.flow.Flow

interface IInboxRepository {

    fun getInbox() : Flow<Resource<List<Inbox>>>

}