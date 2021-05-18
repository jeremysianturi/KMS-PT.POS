package com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.inbox

import com.google.gson.annotations.SerializedName

data class InboxResponse (
    @field:SerializedName("object_identifier")
    val objectIdentifier: Int?,

    @field:SerializedName("application_id")
    val applicationId: String?,

    @field:SerializedName("business_code")
    val buscd: String?,

    @field:SerializedName("actor")
    val actor: String?,

    @field:SerializedName("message")
    val message: String?,

    @field:SerializedName("read_at")
    val readAt: String?,

    @field:SerializedName("action")
    val action: String?,

    @field:SerializedName("user_type")
    val userType: String?,

    @field:SerializedName("change_user")
    val changeUser: String?,

    @field:SerializedName("change_date")
    val changeDate: String?
        )