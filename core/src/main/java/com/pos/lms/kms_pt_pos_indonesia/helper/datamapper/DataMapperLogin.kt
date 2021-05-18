package com.pos.lms.kms_pt_pos_indonesia.helper.datamapper

import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.LoginEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.LoginResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Login

object DataMapperLogin {
    fun mapResponsetoEntities(input: LoginResponse): LoginEntity {
        return LoginEntity(
            accessToken = input.accessToken,
            pernr = input.pernr,
            activeSessions = input.activeSessions,
            tokenType = input.tokenType,
            expiresIn = input.expiresIn

        )
    }

    fun mapEntitiestoDomain(input: LoginEntity?) =
        Login(
            accessToken = input?.accessToken ?: "",
            pernr = input?.pernr ?: "",
            activeSessions = input?.activeSessions ?: 0,
            tokenType = input?.tokenType ?: "",
            expiresIn = input?.expiresIn ?: 0
        )

    fun mapDomaintoEntity(input: Login) = LoginEntity(
        accessToken = input.accessToken,
        pernr = input.pernr,
        activeSessions = input.activeSessions,
        tokenType = input.tokenType,
        expiresIn = input.expiresIn
    )
}