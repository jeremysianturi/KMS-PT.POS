package com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post

import com.google.gson.annotations.SerializedName

data class LoginPost(

    @SerializedName("username")
    private var username: String? = "",

    @SerializedName("password")
    private var password: String? = "",

    @SerializedName("application_id")
    private var applicationId: String? = ""

)