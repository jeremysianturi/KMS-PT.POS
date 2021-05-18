package com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.dao

import androidx.room.*
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.wahana.SearchWahanaEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.wahana.WahanaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchWahanaDao {

    @Query("SELECT * FROM search_wahana")
    fun getSearchWahana(): Flow<List<SearchWahanaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchWahana(searchWahana: List<SearchWahanaEntity>)

    @Query("DELETE FROM search_wahana")
    suspend fun deleteSearchWahana()

    @Transaction
    suspend fun insertAndDeleteSearchWahana(content : List<SearchWahanaEntity>) {
        deleteSearchWahana()
        insertSearchWahana(content)
    }

}

