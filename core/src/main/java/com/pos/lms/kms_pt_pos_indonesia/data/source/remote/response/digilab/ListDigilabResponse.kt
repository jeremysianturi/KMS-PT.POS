package com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.digilab

import com.google.gson.annotations.SerializedName

data class ListDigilabResponse (

    @field:SerializedName("data")
    val data: List<DigilabResponse>,

    @field:SerializedName("status")
    val status: Boolean

        )