package com.pos.lms.kms_pt_pos_indonesia.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PreferenceEntity(
    var username : String? = "",
    var password : String? = "",
    var token: String? = "",
    var activeSessions: Int? = 0,
    var expires: Int? = 0,
    var pernr: String? = "",
    var tokenType: String = "",
    var parId : Int? = 0,
    var isLogin : Boolean? = false
) : Parcelable