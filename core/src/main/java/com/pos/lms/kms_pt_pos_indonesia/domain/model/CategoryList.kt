package com.pos.lms.kms_pt_pos_indonesia.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CategoryList (
    val dataName : String?,
    val dataCode : String?
    ) : Parcelable
