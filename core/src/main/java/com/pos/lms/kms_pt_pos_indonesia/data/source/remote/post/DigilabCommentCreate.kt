package com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post

import com.google.gson.annotations.SerializedName

data class DigilabCommentCreate (

    @field:SerializedName("end_date")
    val endDate: String,

    @field:SerializedName("knowledge")
    val knowledge: Int,

    @field:SerializedName("begin_date")
    val beginDate: String,

    @field:SerializedName("business_code")
    val buscd: String,

    @field:SerializedName("comment")
    val comment: String
        )