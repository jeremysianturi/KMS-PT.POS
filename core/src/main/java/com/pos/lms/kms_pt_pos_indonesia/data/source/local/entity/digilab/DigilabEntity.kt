package com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.digilab

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "digilab")
data class DigilabEntity (

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "object_identifier")
    val objectIdentifier: Int,

    @ColumnInfo(name = "begin_date")
    val beginDate: String,

    @ColumnInfo(name = "end_date")
    val endDate: String,

    @ColumnInfo(name = "bussines_code")
    val buscd: String,

    @ColumnInfo(name = "cases")
    val cases: String,

    @ColumnInfo(name = "solution")
    val solution: String,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "id_knowledge")
    val idKnowledge: Int,

    @ColumnInfo(name = "approve")
    val approve: Int,

    @ColumnInfo(name = "creator")
    val creator: String,

    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,

    @ColumnInfo(name = "address")
    val address: String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "change_date")
    val changeDate: String,

    @ColumnInfo(name = "change_user")
    val changeUser: String

        )