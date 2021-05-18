package com.pos.lms.kms_pt_pos_indonesia.helper.datamapper

import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.multimedia.MultimediaEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.multimedia.MultimediaResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Multimedia

object DataMapperMultimedia {

    fun mapResponsetoEntities(input: List<MultimediaResponse>): List<MultimediaEntity> {
        val multimediaList = ArrayList<MultimediaEntity>()
        input.map {
            val multimedia = MultimediaEntity(
                objectIdentifier = it.objectIdentifier ?: 0,
                beginDate = it.beginDate ?: "",
                endDate = it.endDate ?: "",
                buscd = it.buscd ?: "",
                cases = it.cases ?: "",
                solution = it.solution ?: "",
                type = it.type ?: "",
                idKnowledge = it.idKnowledge ?: 0,
                approve = it.approve ?: 0,
                creator = it.creator ?: "",
                thumbnail = it.thumbnail ?: "",
                address = it.address ?: "",
                status = it.status ?: "",
                changeDate = it.changeDate ?: "",
                changeUser = it.changeUser ?: ""
            )
            multimediaList.add(multimedia)
        }

        return multimediaList
    }

    fun mapEntitiestoDomain(input: List<MultimediaEntity>): List<Multimedia> =
        input.map {
            Multimedia(
                objectIndentifier = it.objectIdentifier,
                beginDate = it.beginDate,
                endDate = it.endDate,
                buscd = it.buscd,
                cases = it.cases,
                solution = it.solution,
                type = it.type,
                idKnowledge = it.idKnowledge,
                approve = it.approve,
                creator = it.creator,
                thumbnail = it.thumbnail,
                address = it.address,
                status = it.status,
                changeDate = it.changeDate,
                changeUser = it.changeUser
            )
        }

}