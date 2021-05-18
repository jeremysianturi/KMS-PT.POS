package com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.wahanaviewers

import com.google.gson.annotations.SerializedName

data class ListWahanaViewersResponse (

    @field:SerializedName("views")
    val views: List<WahanaViewersResponse>

        )