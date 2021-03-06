package com.pos.lms.kms_pt_pos_indonesia.ui.wahana

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.category.CategoryUsecase
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.wahana.WahanaUsecase
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.wahana.searchwahana.SearchWahanaUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

@ExperimentalCoroutinesApi
class WahanaViewModel @ViewModelInject constructor(
    private val wahanaUsecase: WahanaUsecase,
    private val searchWahanaUsecase : SearchWahanaUsecase,
    private val categoryUsecase : CategoryUsecase
    ) : ViewModel() {

//    val searchQuery = MutableStateFlow("")
//
//    private val wahanaFlow = searchQuery.flatMapLatest {
//        wahanaUsecase.getSearchWahana(it)
//    }
//
//    val search = wahanaFlow.asLiveData()

    fun getWahana(type: String) = wahanaUsecase.getWahana(type).asLiveData()


    fun getSearchWahana(type: String,search: String, category: String) =
        searchWahanaUsecase.getSearchWahana(type,search,category).asLiveData()

    fun getCategory(beginDate: String, endDate: String) = categoryUsecase.getCategory(beginDate,endDate).asLiveData()



}