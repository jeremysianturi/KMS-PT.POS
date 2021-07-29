package com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.dao

import androidx.room.*
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.category.CategoryEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.wahana.WahanaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category")
    fun getCategory(): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: List<CategoryEntity>)

    @Query("DELETE FROM category")
    suspend fun deleteCategory()

    @Transaction
    suspend fun insertAndDeleteCategory(content : List<CategoryEntity>) {
        deleteCategory()
        insertCategory(content)
    }

}