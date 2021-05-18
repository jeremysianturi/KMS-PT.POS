package com.pos.lms.kms_pt_pos_indonesia.domain.usecase.wahana.searchwahana

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana
import kotlinx.coroutines.flow.Flow

interface SearchWahanaUsecase {

    fun getSearchWahana(type: String, search: String): Flow<Resource<List<Wahana>>>

}