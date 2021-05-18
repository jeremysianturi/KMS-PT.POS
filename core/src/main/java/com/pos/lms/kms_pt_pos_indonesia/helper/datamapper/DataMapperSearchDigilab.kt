package com.pos.lms.kms_pt_pos_indonesia.helper.datamapper

import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.digilab.SearchDigilabEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.wahana.SearchWahanaEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.digilab.searchdigilab.SearchDigilabResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.searchwahana.SearchWahanaResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Digilab
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana

object DataMapperSearchDigilab {

    fun mapResponsetoEntities(input: List<SearchDigilabResponse>): List<SearchDigilabEntity> {
        val searchDigilabList = ArrayList<SearchDigilabEntity>()
        input.map {
            val searchDigilab = SearchDigilabEntity(
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
            searchDigilabList.add(searchDigilab)
        }

        return searchDigilabList
    }

    fun mapEntitiestoDomain(input: List<SearchDigilabEntity>): List<Digilab> =
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