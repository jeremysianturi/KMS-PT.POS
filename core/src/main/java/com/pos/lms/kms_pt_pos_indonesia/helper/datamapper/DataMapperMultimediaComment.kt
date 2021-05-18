package com.pos.lms.kms_pt_pos_indonesia.helper.datamapper

import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.digilab.digilabcomment.DigilabCommentEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.multimedia.multimediacomment.MultimediaCommentEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.digilab.digilabcomment.DigilabCommentResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.multimedia.multimediacomment.MultimediaCommentResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.DigilabComment
import com.pos.lms.kms_pt_pos_indonesia.domain.model.MultimediaComment

object DataMapperMultimediaComment {

    fun mapResponsetoEntities(input: List<MultimediaCommentResponse>): List<MultimediaCommentEntity> {
        val multimediaCommentList = ArrayList<MultimediaCommentEntity>()
        input.map {
            val multimediaComment = MultimediaCommentEntity(
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
            multimediaCommentList.add(multimediaComment)
        }

        return multimediaCommentList
    }

    fun mapEntitiestoDomain(input: List<MultimediaCommentEntity>): List<MultimediaComment> =
        input.map {
            MultimediaComment(
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