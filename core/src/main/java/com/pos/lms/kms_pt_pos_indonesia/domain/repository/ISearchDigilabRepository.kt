package com.pos.lms.kms_pt_pos_indonesia.domain.repository

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Digilab
import kotlinx.coroutines.flow.Flow

interface ISearchDigilabRepository {

    fun getSearchDigilab(type: String, search : String) : Flow<Resource<List<Digilab>>>

}