package com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.dao

import androidx.room.*
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.digilab.digilabcomment.DigilabCommentEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.wahana.wahanacomment.WahanaCommentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DigilabCommentDao {

    @Query("SELECT * FROM digilab_comment")
    fun getDigilabComment(): Flow<List<DigilabCommentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDigilabComment(digilabComment: List<DigilabCommentEntity>)

    @Query("DELETE FROM digilab_comment")
    suspend fun deleteDigilabComment()

    @Transaction
    suspend fun insertAndDeleteDigilabComment(content : List<DigilabCommentEntity>) {
        deleteDigilabComment()
        insertDigilabComment(content)
    }

}