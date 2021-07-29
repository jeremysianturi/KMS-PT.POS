package com.pos.lms.kms_pt_pos_indonesia.domain.usecase.category

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryUsecase {

    fun getCategory(beginDate: String, endDate: String): Flow<Resource<List<Category>>>

}