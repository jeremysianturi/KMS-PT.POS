package com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.dao

import androidx.room.*
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.wahana.WahanaEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.wahana.wahanaviewers.WahanaViewersEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WahanaViewersDao {

    @Query("SELECT * FROM wahana_viewers")
    fun getWahanaViewers(): Flow<List<WahanaViewersEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWahanaViewers(wahanaViewers: List<WahanaViewersEntity>)

    @Query("DELETE FROM wahana_viewers")
    suspend fun deleteWahanaViewers()

    @Transaction
    suspend fun insertAndDeleteWahanaViewers(content : List<WahanaViewersEntity>) {
        deleteWahanaViewers()
        insertWahanaViewers(content)
    }

}