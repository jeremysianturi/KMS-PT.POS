package com.pos.lms.kms_pt_pos_indonesia.helper.datamapper

import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.category.CategoryEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.articlecategory.CategoryResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Category

object DataMapperCategory {

    fun mapResponsetoEntities(input: List<CategoryResponse>): List<CategoryEntity> {
        val categoryList = ArrayList<CategoryEntity>()
        input.map {
            val category = CategoryEntity(
                id = it.id ?: "",
                value = it.value ?: ""
            )
            categoryList.add(category)
        }

        return categoryList
    }

    fun mapEntitiestoDomain(input: List<CategoryEntity>): List<Category> =
        input.map {
            Category(
                id = it.id,
                value = it.value,
            )
        }

}