package com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.dao

import androidx.room.*
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.ItemParIdEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.LoginEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LoginDao {
    @Query("SELECT * FROM login")
    fun getLogin(): Flow<LoginEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLogin(login : LoginEntity)

    @Query("SELECT * FROM ParId")
    fun getParId(): Flow<List<ItemParIdEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParId(parId : List<ItemParIdEntity>)

    @Query("DELETE FROM ParId")
    suspend fun deleteParId()

    @Transaction
    suspend fun insertAndDeleteStudent(student: List<ItemParIdEntity>) {
        deleteParId()
        insertParId(student)
    }
}