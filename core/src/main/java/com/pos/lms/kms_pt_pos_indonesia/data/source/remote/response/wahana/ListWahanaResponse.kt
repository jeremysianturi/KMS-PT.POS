package com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana

import com.google.gson.annotations.SerializedName

data class ListWahanaResponse (

    @field:SerializedName("data")
    val data: List<WahanaResponse>
        )