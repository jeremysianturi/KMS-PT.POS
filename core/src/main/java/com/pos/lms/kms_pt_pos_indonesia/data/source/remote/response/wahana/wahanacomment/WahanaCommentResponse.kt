package com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.wahanacomment

import com.google.gson.annotations.SerializedName

data class WahanaCommentResponse (

    @field:SerializedName("object_identifier")
    val objectIdentifier: Int,

    @field:SerializedName("begin_date")
    val beginDate: String,

    @field:SerializedName("begin_time")
    val beginTime: String,

    @field:SerializedName("business_code")
    val buscd: String,

    @field:SerializedName("knowledge")
    val knowledge: String,

    @field:SerializedName("owner")
    val owner: String,

    @field:SerializedName("comment")
    val comment: String,

    @field:SerializedName("change_date")
    val changeDate: String,

    @field:SerializedName("change_user")
    val changeUser: String

    )
