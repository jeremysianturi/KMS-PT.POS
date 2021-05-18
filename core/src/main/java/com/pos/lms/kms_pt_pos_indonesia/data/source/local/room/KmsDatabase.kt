package com.pos.lms.kms_pt_pos_indonesia.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.ItemParIdEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.LoginEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.SubmitEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.digilab.DigilabEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.digilab.SearchDigilabEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.digilab.digilabcomment.DigilabCommentEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.inbox.InboxEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.multimedia.MultimediaEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.multimedia.SearchMultimediaEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.multimedia.multimediacomment.MultimediaCommentEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.wahana.SearchWahanaEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.wahana.WahanaEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.wahana.wahanacomment.WahanaCommentEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.entity.wahana.wahanaviewers.WahanaViewersEntity
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.dao.*

/**
 * Created by Muhammad Zaim Milzam on 19/03/21.
 * linkedin : Muhammad Zaim Milzam
 */

@Database(
    entities = [
        LoginEntity::class,
        SubmitEntity::class,
        ItemParIdEntity::class,
        WahanaEntity::class,
        SearchWahanaEntity::class,
        WahanaCommentEntity::class,
        WahanaViewersEntity::class,
        DigilabEntity::class,
        SearchDigilabEntity::class,
        DigilabCommentEntity::class,
        MultimediaEntity::class,
        SearchMultimediaEntity::class,
        MultimediaCommentEntity::class,
        InboxEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class KmsDatabase : RoomDatabase() {

    abstract fun loginDao(): LoginDao

    abstract fun submit(): SubmitDao

    abstract fun wahanaDao() : WahanaDao

    abstract fun searchWahanaDao() : SearchWahanaDao

    abstract fun wahanaCommentDao() : WahanaCommentDao

    abstract fun wahanaViewersDao() : WahanaViewersDao

    abstract fun digilabDao() : DigilabDao

    abstract fun searchDigilabDao() : SearchDigilabDao

    abstract fun digilabCommentDao() : DigilabCommentDao

    abstract fun multimediaDao() : MultimediaDao

    abstract fun searchMultimediaDao() : SearchMultimediaDao

    abstract fun multimediaCommentDao() : MultimediaCommentDao

    abstract fun inboxDao() : InboxDao

}