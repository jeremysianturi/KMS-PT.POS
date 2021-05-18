package com.pos.lms.kms_pt_pos_indonesia.domain.usecase.digilab

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.repository.DigilabRepository
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Digilab
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DigilabInteractor @Inject constructor(private val digilabRepository : DigilabRepository) : DigilabUsecase  {

    override fun getDigilab(type: String): Flow<Resource<List<Digilab>>> =
        digilabRepository.getDigilab(type)

//    override fun getSearchDigilab(search: String): Flow<List<Digilab>> =
//        digilabRepository.getSearchDigilab(search)

}