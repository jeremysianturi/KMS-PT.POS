package com.pos.lms.kms_pt_pos_indonesia.domain.usecase.multimedia

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Multimedia
import kotlinx.coroutines.flow.Flow

interface MultimediaUsecase {

    fun getMultimedia(type: String): Flow<Resource<List<Multimedia>>>

//    fun getSearchMultimedia(search: String): Flow<List<Multimedia>>

}