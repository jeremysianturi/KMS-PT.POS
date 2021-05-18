package com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.wahana.wahanaviewers

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "wahana_viewers")
data class WahanaViewersEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "count")
    val countViewers: Int,

        )