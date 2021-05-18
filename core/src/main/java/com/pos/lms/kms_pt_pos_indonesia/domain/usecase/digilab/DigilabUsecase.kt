package com.pos.lms.kms_pt_pos_indonesia.domain.usecase.digilab

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Digilab
import kotlinx.coroutines.flow.Flow

interface DigilabUsecase {

    fun getDigilab(type: String): Flow<Resource<List<Digilab>>>

//    fun getSearchDigilab(search: String): Flow<List<Digilab>>

}