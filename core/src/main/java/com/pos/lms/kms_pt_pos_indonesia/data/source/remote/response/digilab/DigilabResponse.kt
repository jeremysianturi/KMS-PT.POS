package com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.digilab

import com.google.gson.annotations.SerializedName

data class DigilabResponse (

    @field:SerializedName("object_identifier")
    val objectIdentifier: Int?,

    @field:SerializedName("begin_date")
    val beginDate: String?,

    @field:SerializedName("end_date")
    val endDate: String?,

    @field:SerializedName("bussines_code")
    val buscd: String?,

    @field:SerializedName("cases")
    val cases: String?,

    @field:SerializedName("solution")
    val solution: String?,

    @field:SerializedName("type")
    val type: String?,

    @field:SerializedName("id_knowledge")
    val idKnowledge: Int?,

    @field:SerializedName("approve")
    val approve: Int?,

    @field:SerializedName("creator")
    val creator: String?,

    @field:SerializedName("thumbnail")
    val thumbnail: String?,

    @field:SerializedName("address")
    val address: String?,

    @field:SerializedName("status")
    val status: String?,

    @field:SerializedName("change_date")
    val changeDate: String?,

    @field:SerializedName("change_user")
    val changeUser: String?

        )