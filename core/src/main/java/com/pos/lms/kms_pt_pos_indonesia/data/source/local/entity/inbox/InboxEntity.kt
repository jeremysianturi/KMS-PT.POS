package com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.inbox

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "inbox")
data class InboxEntity (

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "object_identifier")
    val objectIdentifier: Int,

    @ColumnInfo(name = "application_id")
    val applicationId: String,

    @ColumnInfo(name = "business_code")
    val buscd: String,

    @ColumnInfo(name = "actor")
    val actor: String,

    @ColumnInfo(name = "message")
    val message: String,

    @ColumnInfo(name = "read_at")
    val readAt: String,

    @ColumnInfo(name = "action")
    val action: String,

    @ColumnInfo(name = "user_type")
    val userType: String,

    @ColumnInfo(name = "change_user")
    val changeUser: String,

    @ColumnInfo(name = "change_date")
    val changeDate: String
        )