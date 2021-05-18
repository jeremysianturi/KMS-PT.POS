package com.pos.lms.kms_pt_pos_indonesia.helper.datamapper

import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.digilab.digilabcomment.DigilabCommentEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.wahana.wahanacomment.WahanaCommentEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.digilab.digilabcomment.DigilabCommentResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.wahanacomment.WahanaCommentResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.DigilabComment
import com.pos.lms.kms_pt_pos_indonesia.domain.model.WahanaComment

object DataMapperDigilabComment {

    fun mapResponsetoEntities(input: List<DigilabCommentResponse>): List<DigilabCommentEntity> {
        val digilabCommentList = ArrayList<DigilabCommentEntity>()
        input.map {
            val digilabComment = DigilabCommentEntity(
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
            digilabCommentList.add(digilabComment)
        }

        return digilabCommentList
    }

    fun mapEntitiestoDomain(input: List<DigilabCommentEntity>): List<DigilabComment> =
        input.map {
            DigilabComment(
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