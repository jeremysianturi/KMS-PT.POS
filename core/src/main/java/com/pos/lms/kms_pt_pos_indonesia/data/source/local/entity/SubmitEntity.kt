package com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Submit")
data class SubmitEntity(

    @ColumnInfo(name = "message")
    val message: String,

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "status")
    val status: Boolean
)