package com.pos.lms.kms_pt_pos_indonesia.helper.datamapper

import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.wahana.WahanaEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.wahana.wahanaviewers.WahanaViewersEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.WahanaResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.wahanaviewers.WahanaViewersResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana
import com.pos.lms.kms_pt_pos_indonesia.domain.model.WahanaViewers

object DataMapperWahanaViewers {

    fun mapResponsetoEntities(input: List<WahanaViewersResponse>): List<WahanaViewersEntity> {
        val wahanaViewersList = ArrayList<WahanaViewersEntity>()
        input.map {
            val wahanaViewers = WahanaViewersEntity(
                countViewers = it.countViewers ?: 0,

            )
            wahanaViewersList.add(wahanaViewers)
        }

        return wahanaViewersList
    }

    fun mapEntitiestoDomain(input: List<WahanaViewersEntity>): List<WahanaViewers> =
        input.map {
            WahanaViewers(
                count = it.countViewers,
            )
        }
}