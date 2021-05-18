package com.pos.lms.kms_pt_pos_indonesia.domain.usecase.multimedia.searchmultimedia

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.repository.SearchMultimediaRepository
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Multimedia
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMultimediaInteractor @Inject constructor(private val searchMultimediaRepository : SearchMultimediaRepository) :
    SearchMultimediaUsecase {

    override fun getSearchMultimedia(type: String, search: String): Flow<Resource<List<Multimedia>>> =
        searchMultimediaRepository.getSearchMultimedia(type,search)

}