package com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.digilab.searchdigilab

import com.google.gson.annotations.SerializedName

data class ListSearchDigilabResponse (
    @field:SerializedName("data")
    val data: List<SearchDigilabResponse>,

    @field:SerializedName("status")
    val status: Boolean
        )