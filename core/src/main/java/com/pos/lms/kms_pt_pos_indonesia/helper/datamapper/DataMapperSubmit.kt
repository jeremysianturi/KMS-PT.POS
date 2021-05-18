package com.pos.lms.kms_pt_pos_indonesia.helper.datamapper

import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.LoginEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.SubmitEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.SubmitResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Login
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Submit

object DataMapperSubmit {
    fun mapResponsetoEntities(input: SubmitResponse): SubmitEntity
    {
        return SubmitEntity(
            status = input.status,
            message = input.message
        )
    }


    fun mapEntitiestoDomain(input: SubmitEntity?) =
        Submit(
            status = input?.status ?: false,
            message = input?.message ?: ""
        )

    fun mapDomaintoEntity(input: Login) = LoginEntity(
        accessToken = input.accessToken,
        pernr = input.pernr,
        activeSessions = input.activeSessions,
        tokenType = input.tokenType,
        expiresIn = input.expiresIn
    )
}