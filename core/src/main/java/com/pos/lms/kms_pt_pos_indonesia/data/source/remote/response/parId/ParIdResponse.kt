package com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.parId

import com.google.gson.annotations.SerializedName

data class ParIdResponse(
    @field:SerializedName("")
    val data: List<ItemParId>

)
data class ItemParId(
    @field:SerializedName("access_token")
    val accessToken: String,
    @field:SerializedName("username")
    val username: String,
    @field:SerializedName("type")
    val type: String,
    @field:SerializedName("id")
    val id: Int
)
