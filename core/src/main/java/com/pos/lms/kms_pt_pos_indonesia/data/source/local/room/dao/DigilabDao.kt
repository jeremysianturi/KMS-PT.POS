package com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.dao

import androidx.room.*
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.digilab.DigilabEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DigilabDao {

    @Query("SELECT * FROM digilab")
    fun getDigilab() : Flow<List<DigilabEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDigilab(digilab: List<DigilabEntity>)

    @Query("DELETE FROM digilab")
    suspend fun deleteDigilab()

    @Transaction
    suspend fun insertAndDeleteDigilab(content : List<DigilabEntity>) {
        deleteDigilab()
        insertDigilab(content)
    }

//    @Transaction
//    @Query("SELECT * FROM digilab where cases LIKE '%'|| :search || '%'")        // end_date jangan lupa sesuain
//    fun getSearchDigilab(search: String): Flow<List<DigilabEntity>>
}