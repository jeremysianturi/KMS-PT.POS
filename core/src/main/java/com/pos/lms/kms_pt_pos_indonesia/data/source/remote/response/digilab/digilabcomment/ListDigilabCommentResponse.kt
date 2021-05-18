package com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.digilab.digilabcomment

import com.google.gson.annotations.SerializedName
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.wahanacomment.WahanaCommentResponse

data class ListDigilabCommentResponse (

    @field:SerializedName("data")
    val data: List<DigilabCommentResponse>

        )