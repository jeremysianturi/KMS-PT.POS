package com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.dao

import androidx.room.*
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.SubmitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SubmitDao {

    @Query("SELECT * FROM submit")
    fun getSubmit(): Flow<SubmitEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubmit(submit : SubmitEntity)

    @Query("DELETE FROM submit")
    fun deleteSubmit()

    @Transaction
    suspend fun insertAndDeleteMentoringChat(student: SubmitEntity) {
        deleteSubmit()
        insertSubmit(student)
    }

}