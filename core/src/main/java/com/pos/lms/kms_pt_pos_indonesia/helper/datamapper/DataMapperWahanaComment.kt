package com.pos.lms.kms_pt_pos_indonesia.helper.datamapper

import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.wahana.WahanaEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.wahana.wahanacomment.WahanaCommentEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.WahanaResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.wahanacomment.WahanaCommentResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana
import com.pos.lms.kms_pt_pos_indonesia.domain.model.WahanaComment

object DataMapperWahanaComment {

    fun mapResponsetoEntities(input: List<WahanaCommentResponse>): List<WahanaCommentEntity> {
        val wahanaCommentList = ArrayList<WahanaCommentEntity>()
        input.map {
            val wahanaComment = WahanaCommentEntity(
                objectIdentifier = it.objectIdentifier,
                beginDate = it.beginDate,
                beginTime = it.beginTime,
                buscd = it.buscd,
                knowledge = it.knowledge,
                owner = it.owner,
                comment = it.comment,
                changeDate = it.changeDate,
                changeUser = it.changeUser
            )
            wahanaCommentList.add(wahanaComment)
        }

        return wahanaCommentList
    }

    fun mapEntitiestoDomain(input: List<WahanaCommentEntity>): List<WahanaComment> =
        input.map {
            WahanaComment(
                objectIdentifier = it.objectIdentifier,
                beginDate = it.beginDate,
                beginTime = it.beginTime,
                buscd = it.buscd,
                knowledge = it.knowledge,
                owner = it.owner,
                comment = it.comment,
                changeDate = it.changeDate,
                changeUser = it.changeUser
            )
        }

}