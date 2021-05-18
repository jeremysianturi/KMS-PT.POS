package com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.dao

import androidx.room.*
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.wahana.wahanacomment.WahanaCommentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WahanaCommentDao {

    @Query("SELECT * FROM wahana_comment")
    fun getWahanaComment(): Flow<List<WahanaCommentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWahanaComment(wahanaComment: List<WahanaCommentEntity>)

    @Query("DELETE FROM wahana_comment")
    suspend fun deleteWahanaComment()

    @Transaction
    suspend fun insertAndDeleteWahanaComment(content : List<WahanaCommentEntity>) {
        deleteWahanaComment()
        insertWahanaComment(content)
    }

}