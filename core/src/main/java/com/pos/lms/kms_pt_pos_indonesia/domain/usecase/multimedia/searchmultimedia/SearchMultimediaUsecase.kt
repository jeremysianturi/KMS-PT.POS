package com.pos.lms.kms_pt_pos_indonesia.domain.usecase.multimedia.searchmultimedia

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Multimedia
import kotlinx.coroutines.flow.Flow

interface SearchMultimediaUsecase {

    fun getSearchMultimedia(type: String, search: String): Flow<Resource<List<Multimedia>>>


}