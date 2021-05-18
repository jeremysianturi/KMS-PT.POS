package com.pos.lms.kms_pt_pos_indonesia.helper.datamapper

import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.inbox.InboxEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.inbox.InboxResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Inbox

object DataMapperInbox {

    fun mapResponsetoEntities(input: List<InboxResponse>): List<InboxEntity> {
        val inboxList = ArrayList<InboxEntity>()
        input.map {
            val inbox = InboxEntity(
                objectIdentifier = it.objectIdentifier ?: 0,
                applicationId = it.applicationId ?: "",
                buscd = it.buscd ?: "",
                actor = it.actor ?: "",
                message = it.message ?: "",
                readAt = it.readAt ?: "",
                action = it.action ?: "",
                userType = it.userType ?: "",
                changeUser = it.changeUser ?: "",
                changeDate = it.changeDate ?: ""
            )
            inboxList.add(inbox)
        }

        return inboxList
    }

    fun mapEntitiestoDomain(input: List<InboxEntity>): List<Inbox> =
        input.map {
            Inbox(
                objectIdentifier = it.objectIdentifier,
                applicationId = it.applicationId,
                buscd = it.buscd,
                actor = it.actor,
                message = it.message,
                readAt = it.readAt,
                action = it.action,
                userType = it.userType,
                changeUser = it.changeUser,
                changeDate = it.changeDate
            )
        }

}