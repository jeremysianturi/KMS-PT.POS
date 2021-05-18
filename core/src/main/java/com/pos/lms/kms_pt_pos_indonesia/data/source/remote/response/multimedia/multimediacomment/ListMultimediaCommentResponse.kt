package com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.multimedia.multimediacomment

import com.google.gson.annotations.SerializedName
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.digilab.digilabcomment.DigilabCommentResponse

data class ListMultimediaCommentResponse (

    @field:SerializedName("data")
    val data: List<MultimediaCommentResponse>

        )