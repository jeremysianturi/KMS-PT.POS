package com.pos.lms.kms_pt_pos_indonesia.domain.usecase.multimedia

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.repository.MultimediaRepository
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Multimedia
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MultimediaInteractor @Inject constructor(private val multimediaRepository : MultimediaRepository) : MultimediaUsecase {

    override fun getMultimedia(type: String): Flow<Resource<List<Multimedia>>> =
    multimediaRepository.getMultimedia(type)

//    override fun getSearchMultimedia(search: String): Flow<List<Multimedia>> =
//    multimediaRepository.getSearchMultimedia(search)

}