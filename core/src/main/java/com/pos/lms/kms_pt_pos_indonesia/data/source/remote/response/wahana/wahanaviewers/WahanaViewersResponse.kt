package com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.wahanaviewers

import com.google.gson.annotations.SerializedName

data class WahanaViewersResponse (

    @field:SerializedName("COUNT")
    val countViewers: Int?
    )
