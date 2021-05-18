package com.pos.lms.kms_pt_pos_indonesia.helper.datamapper

import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.multimedia.MultimediaEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.multimedia.SearchMultimediaEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.multimedia.MultimediaResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.multimedia.searchmultimedia.SearchMultimediaResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Multimedia

object DataMapperSearchMultimedia {

    fun mapResponsetoEntities(input: List<SearchMultimediaResponse>): List<SearchMultimediaEntity> {
        val searchMultimediaList = ArrayList<SearchMultimediaEntity>()
        input.map {
            val searchMultimedia = SearchMultimediaEntity(
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
            searchMultimediaList.add(searchMultimedia)
        }

        return searchMultimediaList
    }

    fun mapEntitiestoDomain(input: List<SearchMultimediaEntity>): List<Multimedia> =
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