package com.pos.lms.kms_pt_pos_indonesia.domain.repository

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana
import kotlinx.coroutines.flow.Flow

interface ISearchWahanaRepository {

    fun getSearchWahana(type: String, search : String, category: String) : Flow<Resource<List<Wahana>>>


}