package com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.multimedia.searchmultimedia

import com.google.gson.annotations.SerializedName

data class ListSearchMultimediaResponse (
    @field:SerializedName("data")
    val data: List<SearchMultimediaResponse>,

    @field:SerializedName("status")
    val status: Boolean
        )