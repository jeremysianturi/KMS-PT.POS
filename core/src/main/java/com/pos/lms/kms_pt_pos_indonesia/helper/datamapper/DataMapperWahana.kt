package com.pos.lms.kms_pt_pos_indonesia.helper.datamapper

import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.wahana.WahanaEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.WahanaResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana

object DataMapperWahana {

    fun mapResponsetoEntities(input: List<WahanaResponse>): List<WahanaEntity> {
        val wahanaList = ArrayList<WahanaEntity>()
        input.map {
            val wahana = WahanaEntity(
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
            wahanaList.add(wahana)
        }

        return wahanaList
    }

    fun mapEntitiestoDomain(input: List<WahanaEntity>): List<Wahana> =
        input.map {
            Wahana(
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