package com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse (

    @field:SerializedName("access_token")
    val accessToken: String,

    @field:SerializedName("pernr")
    val pernr: String,

    @field:SerializedName("active_sessions")
    val activeSessions: Int,

    @field:SerializedName("token_type")
    val tokenType: String,

    @field:SerializedName("expires_in")
    val expiresIn: Int

        )