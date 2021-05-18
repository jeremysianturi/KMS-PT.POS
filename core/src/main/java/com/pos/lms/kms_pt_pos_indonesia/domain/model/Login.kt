package com.pos.lms.kms_pt_pos_indonesia.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Login(
    val accessToken: String,
    val pernr: String,
    val activeSessions: Int,
    val tokenType: String,
    val expiresIn: Int
) : Parcelable