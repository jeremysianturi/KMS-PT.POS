package com.pos.lms.kms_pt_pos_indonesia.domain.repository

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Multimedia
import kotlinx.coroutines.flow.Flow

interface ISearchMultimediaRepository {

    fun getSearchMultimedia(type : String, search: String) : Flow<Resource<List<Multimedia>>>


}