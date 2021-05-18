package com.pos.lms.kms_pt_pos_indonesia.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WahanaComment (
    val objectIdentifier : Int,
    val beginDate : String,
    val beginTime : String,
    val buscd : String,
    val knowledge : String,
    val owner : String,
    val comment : String,
    val changeDate : String,
    val changeUser : String
        ) : Parcelable