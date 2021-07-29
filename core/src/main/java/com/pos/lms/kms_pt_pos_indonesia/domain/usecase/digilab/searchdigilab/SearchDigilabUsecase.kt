package com.pos.lms.kms_pt_pos_indonesia.domain.usecase.digilab.searchdigilab

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Digilab
import kotlinx.coroutines.flow.Flow

interface SearchDigilabUsecase {

    fun getSearchDigilab(type: String, search: String, category: String): Flow<Resource<List<Digilab>>>


}