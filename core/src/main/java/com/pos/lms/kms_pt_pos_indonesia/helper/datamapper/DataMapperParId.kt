package com.pos.lms.kms_pt_pos_indonesia.helper.datamapper

import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.ItemParIdEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.parId.ItemParId
import com.pos.lms.kms_pt_pos_indonesia.domain.model.ParId

object DataMapperParId {
    fun mapResponsesToEntities(input: List<ItemParId>): List<ItemParIdEntity> {
        val parIdList = ArrayList<ItemParIdEntity>()
        input.map {
            val parId = ItemParIdEntity(
                accessToken = it.accessToken,
                username = it.username,
                type = it.type,
                id = it.id
            )
            parIdList.add(parId)
        }
        return parIdList
    }

    fun mapEntitiesToDomain(input: List<ItemParIdEntity>): List<ParId> =
        input.map {
            ParId(
                accessToken = it.accessToken,
                username = it.username,
                type = it.type,
                id = it.id
            )
        }

    fun mapDomainToEntity(input: ParId) = ItemParIdEntity(
        accessToken = input.accessToken,
        username = input.username,
        type = input.type,
        id = input.id
    )
}