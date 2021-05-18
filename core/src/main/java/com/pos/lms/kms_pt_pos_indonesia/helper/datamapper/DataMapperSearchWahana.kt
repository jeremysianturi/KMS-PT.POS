package com.pos.lms.kms_pt_pos_indonesia.helper.datamapper

import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.wahana.SearchWahanaEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.searchwahana.SearchWahanaResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana

object DataMapperSearchWahana {

    fun mapResponsetoEntities(input: List<SearchWahanaResponse>): List<SearchWahanaEntity> {
        val searchWahanaList = ArrayList<SearchWahanaEntity>()
        input.map {
            val searchWahana = SearchWahanaEntity(
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
            searchWahanaList.add(searchWahana)
        }

        return searchWahanaList
    }

    fun mapEntitiestoDomain(input: List<SearchWahanaEntity>): List<Wahana> =
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