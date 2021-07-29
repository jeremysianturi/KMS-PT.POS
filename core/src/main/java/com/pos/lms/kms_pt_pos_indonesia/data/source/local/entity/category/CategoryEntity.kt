package com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "category")
data class CategoryEntity (

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "value")
    val value: String

        )