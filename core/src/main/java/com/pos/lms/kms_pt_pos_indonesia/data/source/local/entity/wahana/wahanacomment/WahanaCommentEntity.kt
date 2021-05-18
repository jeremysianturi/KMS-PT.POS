package com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.wahana.wahanacomment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "wahana_comment")
data class WahanaCommentEntity (

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "object_identifier")
    val objectIdentifier: Int,

    @ColumnInfo(name = "begin_date")
    val beginDate: String,

    @ColumnInfo(name = "begin_time")
    val beginTime: String,

    @ColumnInfo(name = "business_code")
    val buscd: String,

    @ColumnInfo(name = "knowledge")
    val knowledge: String,

    @ColumnInfo(name = "owner")
    val owner: String,

    @ColumnInfo(name = "comment")
    val comment: String,

    @ColumnInfo(name = "change_date")
    val changeDate: String,

    @ColumnInfo(name = "change_user")
    val changeUser: String

        )