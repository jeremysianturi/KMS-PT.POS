package com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.articlecategory

import com.google.gson.annotations.SerializedName

data class CategoryResponse (

    @field:SerializedName("id")
    val id: String?,

    @field:SerializedName("value")
    val value: String?

        )