package com.pos.lms.kms_pt_pos_indonesia.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wahana (
    val objectIndentifier : Int,
    val beginDate : String,
    val endDate : String,
    val buscd : String,
    val cases : String,
    val solution : String,
    val type : String,
    val idKnowledge : Int,
    val approve : Int,
    val creator : String,
    val thumbnail : String,
    val address : String,
    val status : String,
    val changeDate : String,
    val changeUser : String
    ) : Parcelable