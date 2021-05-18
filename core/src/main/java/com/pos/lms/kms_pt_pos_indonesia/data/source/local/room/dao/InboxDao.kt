package com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.dao

import androidx.room.*
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.inbox.InboxEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.wahana.WahanaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InboxDao {

    @Query("SELECT * FROM inbox")
    fun getInbox(): Flow<List<InboxEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInbox(inbox: List<InboxEntity>)

    @Query("DELETE FROM inbox")
    suspend fun deleteInbox()

    @Transaction
    suspend fun insertAndDeleteInbox(content : List<InboxEntity>) {
        deleteInbox()
        insertInbox(content)
    }

    @Transaction
    @Query("SELECT * FROM inbox where actor LIKE '%'|| :search || '%'")
    fun getSearchInbox(search: String): Flow<List<InboxEntity>>

}