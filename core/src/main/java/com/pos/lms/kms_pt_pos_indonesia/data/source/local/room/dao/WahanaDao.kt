package com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.dao

import androidx.room.*
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.wahana.WahanaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WahanaDao {

    @Query("SELECT * FROM wahana")
    fun getWahana(): Flow<List<WahanaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWahana(wahana: List<WahanaEntity>)

    @Query("DELETE FROM wahana")
    suspend fun deleteWahana()

    @Transaction
    suspend fun insertAndDeleteWahana(content : List<WahanaEntity>) {
        deleteWahana()
        insertWahana(content)
    }

//    @Transaction
//    @Query("SELECT * FROM wahana where cases LIKE '%'|| :search || '%'")
//    fun getSearchWahana(search: String): Flow<List<WahanaEntity>>

}