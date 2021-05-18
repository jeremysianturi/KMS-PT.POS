package com.pos.lms.kms_pt_pos_indonesia.domain.repository

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Multimedia
import kotlinx.coroutines.flow.Flow

interface IMultimediaRepository {

    fun getMultimedia(type : String) : Flow<Resource<List<Multimedia>>>

//    fun getSearchMultimedia(search: String): Flow<List<Multimedia>>


}