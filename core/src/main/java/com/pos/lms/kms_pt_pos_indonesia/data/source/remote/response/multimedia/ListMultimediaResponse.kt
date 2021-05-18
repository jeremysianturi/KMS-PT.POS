package com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.multimedia

import com.google.gson.annotations.SerializedName

data class ListMultimediaResponse (

    @field:SerializedName("data")
    val data: List<MultimediaResponse>,

    @field:SerializedName("status")
    val status: Boolean

        )