package com.pos.lms.kms_pt_pos_indonesia.domain.usecase.category

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.repository.CategoryRepository
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryInteractor @Inject constructor(private val categoryRepository : CategoryRepository) : CategoryUsecase {

    override fun getCategory(beginDate: String, endDate: String): Flow<Resource<List<Category>>> =
        categoryRepository.getCategory(beginDate, endDate)

}