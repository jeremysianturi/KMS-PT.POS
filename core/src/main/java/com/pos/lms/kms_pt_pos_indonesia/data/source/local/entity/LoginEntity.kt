package com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Login")
data class LoginEntity(
    @ColumnInfo(name = "accessToken")
    val accessToken: String? = "",

    @ColumnInfo(name = "pernr")
    val pernr: String? = "",

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "activeSessions")
    val activeSessions: Int? = 0,

    @ColumnInfo(name = "tokenType")
    val tokenType: String? = "",

    @ColumnInfo(name = "expiresIn")
    val expiresIn: Int? = 0
)