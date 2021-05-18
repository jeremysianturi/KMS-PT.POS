package com.pos.lms.kms_pt_pos_indonesia.domain.usecase.digilab.searchdigilab

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.repository.SearchDigilabRepository
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Digilab
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchDigilabInteractor @Inject constructor(private val searchDigilabRepository : SearchDigilabRepository) : SearchDigilabUsecase {

    override fun getSearchDigilab(type: String, search: String): Flow<Resource<List<Digilab>>> =
        searchDigilabRepository.getSearchDigilab(type,search)

}