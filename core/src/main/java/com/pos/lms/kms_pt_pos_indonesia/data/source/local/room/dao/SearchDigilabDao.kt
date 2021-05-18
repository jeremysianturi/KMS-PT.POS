package com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.dao

import androidx.room.*
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.digilab.SearchDigilabEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchDigilabDao {

    @Query("SELECT * FROM search_digilab")
    fun getSearchDigilab() : Flow<List<SearchDigilabEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchDigilab(searchDigilab: List<SearchDigilabEntity>)

    @Query("DELETE FROM search_digilab")
    suspend fun deleteSearchDigilab()

    @Transaction
    suspend fun insertAndDeleteSearchDigilab(content : List<SearchDigilabEntity>) {
        deleteSearchDigilab()
        insertSearchDigilab(content)
    }

}