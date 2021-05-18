package com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.dao

import androidx.room.*
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.digilab.digilabcomment.DigilabCommentEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.multimedia.multimediacomment.MultimediaCommentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MultimediaCommentDao {

    @Query("SELECT * FROM multimedia_comment")
    fun getMultimediaComment(): Flow<List<MultimediaCommentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultimediaComment(multimediaComment: List<MultimediaCommentEntity>)

    @Query("DELETE FROM multimedia_comment")
    suspend fun deleteMultimediaComment()

    @Transaction
    suspend fun insertAndDeleteMultimediaComment(content : List<MultimediaCommentEntity>) {
        deleteMultimediaComment()
        insertMultimediaComment(content)
    }

}