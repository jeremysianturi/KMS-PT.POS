package com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PersonalResponse(

    @field:SerializedName("data")
    val data: List<DataItem>
)

data class Username(

    @field:SerializedName("end_date")
    val endDate: String,

    @field:SerializedName("object_identifier")
    val objectIdentifier: Any,

    @field:SerializedName("birth_city")
    val birthCity: String,

    @field:SerializedName("gender")
    val gender: String,

    @field:SerializedName("change_date")
    val changeDate: String,

    @field:SerializedName("begin_date")
    val beginDate: String,

    @field:SerializedName("birth_date")
    val birthDate: String,

    @field:SerializedName("business_code")
    val businessCode: String,

    @field:SerializedName("language")
    val language: String,

    @field:SerializedName("personnel_number")
    val personnelNumber: String,

    @field:SerializedName("personnel_number_reference")
    val personnelNumberReference: String,

    @field:SerializedName("blood")
    val blood: String,

    @field:SerializedName("religion")
    val religion: String,

    @field:SerializedName("complete_name")
    val completeName: String,

    @field:SerializedName("marital_status")
    val maritalStatus: String,

    @field:SerializedName("rhesus")
    val rhesus: String,

    @field:SerializedName("marital_date")
    val maritalDate: String,

    @field:SerializedName("nationality")
    val nationality: String,

    @field:SerializedName("tribe")
    val tribe: String,

    @field:SerializedName("nickname")
    val nickname: String,

    @field:SerializedName("change_user")
    val changeUser: String
)

data class DataItem(

    @field:SerializedName("end_date")
    val endDate: String,

    @field:SerializedName("business_name")
    val businessName: String,

    @field:SerializedName("object_identifier")
    val objectIdentifier: Int,

    @field:SerializedName("role")
    val role: List<String>,

    @field:SerializedName("business_address")
    val businessAddress: Any,

    @field:SerializedName("change_date")
    val changeDate: String,

    @field:SerializedName("begin_date")
    val beginDate: String,

    @field:SerializedName("change_user")
    val changeUser: String,

    @field:SerializedName("company")
    val company: String,

    @field:SerializedName("email")
    val email: Any,

    @field:SerializedName("company_address")
    val companyAddress: Any,

    @field:SerializedName("username")
    val username: Username
)
