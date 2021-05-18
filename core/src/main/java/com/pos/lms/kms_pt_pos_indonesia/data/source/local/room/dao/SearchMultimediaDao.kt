package com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.dao

import androidx.room.*
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.multimedia.MultimediaEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.multimedia.SearchMultimediaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchMultimediaDao {

    @Query("SELECT * FROM search_multimedia")
    fun getSearchMultimedia(): Flow<List<SearchMultimediaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchMultimedia(searchMultimedia : List<SearchMultimediaEntity>)

    @Query("DELETE FROM search_multimedia")
    suspend fun deleteSearchMultimedia()

    @Transaction
    suspend fun insertAndDeleteSearchMultimedia(content : List<SearchMultimediaEntity>) {
        deleteSearchMultimedia()
        insertSearchMultimedia(content)
    }

}