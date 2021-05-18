package com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.searchwahana

import com.google.gson.annotations.SerializedName

data class ListSearchWahanaResponse (

    @field:SerializedName("data")
    val data: List<SearchWahanaResponse>
        )