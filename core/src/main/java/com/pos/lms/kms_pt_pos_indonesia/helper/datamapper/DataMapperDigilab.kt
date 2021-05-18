package com.pos.lms.kms_pt_pos_indonesia.helper.datamapper

import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.digilab.DigilabEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.digilab.DigilabResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Digilab

object DataMapperDigilab {

    fun mapResponsetoEntities(input: List<DigilabResponse>): List<DigilabEntity> {
        val digilabList = ArrayList<DigilabEntity>()
        input.map {
            val digilab = DigilabEntity(
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
            digilabList.add(digilab)
        }

        return digilabList
    }

    fun mapEntitiestoDomain(input: List<DigilabEntity>): List<Digilab> =
        input.map {
            Digilab(
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