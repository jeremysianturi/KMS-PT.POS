package com.pos.lms.kms_pt_pos_indonesia.domain.usecase.wahana.searchwahana

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.repository.SearchWahanaRepository
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchWahanaInteractor @Inject constructor(private val searchWahanaRepository : SearchWahanaRepository) : SearchWahanaUsecase {

    override fun getSearchWahana(type: String, search: String): Flow<Resource<List<Wahana>>> =
        searchWahanaRepository.getSearchWahana(type,search)


}