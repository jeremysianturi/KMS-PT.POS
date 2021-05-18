package com.pos.lms.kms_pt_pos_indonesia.ui.multimedia

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.multimedia.MultimediaUsecase
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.multimedia.searchmultimedia.SearchMultimediaUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

@ExperimentalCoroutinesApi
class MultimediaViewModel @ViewModelInject constructor(
    private val multimediaUsecase: MultimediaUsecase,
    private val searchMultimediaUsecase: SearchMultimediaUsecase
    ) : ViewModel() {

//    val searchQuery = MutableStateFlow("")
//
//    private val multimediaFlow = searchQuery.flatMapLatest {
//        multimediaUsecase.getSearchMultimedia(it)
//    }
//
//    val search = multimediaFlow.asLiveData()

    fun getMultimedia(type: String) = multimediaUsecase.getMultimedia(type).asLiveData()

    fun getSearchMultimedia(type: String,search: String) = searchMultimediaUsecase.getSearchMultimedia(type,search).asLiveData()

}