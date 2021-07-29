package com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.articlecategory

import com.google.gson.annotations.SerializedName
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.WahanaResponse

data class ListCategoryResponse (

    @field:SerializedName("data")
    val data: List<CategoryResponse>

        )