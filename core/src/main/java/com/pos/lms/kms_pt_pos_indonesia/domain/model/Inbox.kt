package com.pos.lms.kms_pt_pos_indonesia.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Inbox (
    val objectIdentifier : Int,
    val applicationId : String,
    val buscd : String,
    val actor : String,
    val message : String,
    val readAt : String,
    val action : String,
    val userType : String,
    val changeUser : String,
    val changeDate : String
        ) : Parcelable