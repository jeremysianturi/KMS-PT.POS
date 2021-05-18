package com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.dao

import androidx.room.*
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.multimedia.MultimediaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MultimediaDao {

    @Query("SELECT * FROM multimedia")
    fun getMultimedia(): Flow<List<MultimediaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultimedia(multimedia : List<MultimediaEntity>)

    @Query("DELETE FROM multimedia")
    suspend fun deleteMultimedia()

    @Transaction
    suspend fun insertAndDeleteMultimedia(content : List<MultimediaEntity>) {
        deleteMultimedia()
        insertMultimedia(content)
    }

//    @Transaction
//    @Query("SELECT * FROM multimedia where cases LIKE '%'|| :search || '%'")         // end data jangan lupa di ganti
//    fun getSearchMultimedia(search: String): Flow<List<MultimediaEntity>>

}