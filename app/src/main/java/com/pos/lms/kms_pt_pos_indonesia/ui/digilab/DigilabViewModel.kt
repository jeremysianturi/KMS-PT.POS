package com.pos.lms.kms_pt_pos_indonesia.ui.digilab

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.category.CategoryUsecase
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.digilab.DigilabUsecase
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.digilab.searchdigilab.SearchDigilabUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

@ExperimentalCoroutinesApi
class DigilabViewModel @ViewModelInject constructor(
    private val digilabUsecase : DigilabUsecase,
    private val searchDigilabUsecase : SearchDigilabUsecase,
    private val categoryUsecase : CategoryUsecase
) : ViewModel() {

//    val searchQuery = MutableStateFlow("")
//
//    private val digilabFlow = searchQuery.flatMapLatest {
//        digilabUsecase.getSearchDigilab(it)
//    }
//
//    val search = digilabFlow.asLiveData()

    fun getDigilab(type: String) = digilabUsecase.getDigilab(type).asLiveData()

    fun getSearchDigilab(type: String,search: String, category: String) =
        searchDigilabUsecase.getSearchDigilab(type,search,category).asLiveData()

    fun getCategory(beginDate: String, endDate: String) = categoryUsecase.getCategory(beginDate,endDate).asLiveData()


}