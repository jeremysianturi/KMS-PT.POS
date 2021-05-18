package com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.wahanacomment

import com.google.gson.annotations.SerializedName
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.WahanaResponse

data class ListWahanaCommentResponse (

    @field:SerializedName("data")
    val data: List<WahanaCommentResponse>

        )