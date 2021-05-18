package com.pos.lms.kms_pt_pos_indonesia.data.source.local.room

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
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Muhammad Zaim Milzam on 19/03/21.
 * linkedin : Muhammad Zaim Milzam
 */

@Singleton
class LocalDataSource @Inject constructor(
    private val mLoginDao: LoginDao,
    private val mSubmitDao: SubmitDao,
    private val mWahanaDao: WahanaDao,
    private val mSearchWahanaDao: SearchWahanaDao,
    private val mWahanaCommentDao: WahanaCommentDao,
    private val mWahanaViewersDao: WahanaViewersDao,
    private val mDigilabDao: DigilabDao,
    private val mSearchDigilabDao: SearchDigilabDao,
    private val mDigilabCommentDao: DigilabCommentDao,
    private val mMultimediaDao: MultimediaDao,
    private val mSearchMultimediaDao: SearchMultimediaDao,
    private val mMultimediaCommentDao: MultimediaCommentDao,
    private val mInboxDao: InboxDao
    ) {

    // login
    fun getLogin(): Flow<LoginEntity> = mLoginDao.getLogin()
    suspend fun insertLogin(login: LoginEntity) = mLoginDao.insertLogin(login)

    fun getParId(): Flow<List<ItemParIdEntity>> = mLoginDao.getParId()
    suspend fun insertParId(parId: List<ItemParIdEntity>) = mLoginDao.insertAndDeleteStudent(parId)
    // login

    // submit
    fun deleteSubmit() = mSubmitDao.deleteSubmit()
    // submit

    // for change password / submit
    fun getSubmitResponse(): Flow<SubmitEntity> = mSubmitDao.getSubmit()
    suspend fun insertSubmitResponse(submitEntity: SubmitEntity) = mSubmitDao.insertAndDeleteMentoringChat(submitEntity)
    // for change password / submit

    // --------------------------------------- Wahana ----------------------------------------------
    // wahana
    fun getWahana(): Flow<List<WahanaEntity>> = mWahanaDao.getWahana()
    suspend fun insertWahana(wahana : List<WahanaEntity>) = mWahanaDao.insertAndDeleteWahana(wahana)

    suspend fun deleteWahana() = mWahanaDao.deleteWahana()

//    fun getSearchWahana(search: String): Flow<List<WahanaEntity>> = mWahanaDao.getSearchWahana(search)
    // wahana

    // search wahana
    fun getSearchWahana(): Flow<List<SearchWahanaEntity>> = mSearchWahanaDao.getSearchWahana()
    suspend fun insertSearchWahana(searchWahana : List<SearchWahanaEntity>) = mSearchWahanaDao.insertAndDeleteSearchWahana(searchWahana)
    suspend fun deleteSearchWahana() = mSearchWahanaDao.deleteSearchWahana()
    // search wahana

    // wahana comment
    fun getWahanaComment(): Flow<List<WahanaCommentEntity>> = mWahanaCommentDao.getWahanaComment()
    suspend fun insertWahanaComment(wahanaComment : List<WahanaCommentEntity>) = mWahanaCommentDao.insertAndDeleteWahanaComment(wahanaComment)
    suspend fun deleteWahanaComment() = mWahanaCommentDao.deleteWahanaComment()

    fun getSubmitWahanaCommentResponse(): Flow<SubmitEntity> = mSubmitDao.getSubmit()
    suspend fun insertSubmitWahanaCommentResponse(submitEntity: SubmitEntity) = mSubmitDao.insertAndDeleteMentoringChat(submitEntity)
    // wahana comment

    // wahana viewers
    fun getWahanaViewers(): Flow<List<WahanaViewersEntity>> = mWahanaViewersDao.getWahanaViewers()
    suspend fun insertWahanaViewers(wahanaViewers: List<WahanaViewersEntity>) = mWahanaViewersDao.insertAndDeleteWahanaViewers(wahanaViewers)

    suspend fun deleteWahanaViewers() = mWahanaViewersDao.deleteWahanaViewers()
    // wahana viewers
    // --------------------------------------- Wahana ----------------------------------------------

    // --------------------------------------- Digilab ----------------------------------------------
    // digilab
    fun getDigilab(): Flow<List<DigilabEntity>> = mDigilabDao.getDigilab()
    suspend fun insertDigilab(digilab : List<DigilabEntity>) = mDigilabDao.insertAndDeleteDigilab(digilab)

    suspend fun deleteDigilab() = mDigilabDao.deleteDigilab()

//    fun getSearchDigilab(search: String): Flow<List<DigilabEntity>> = mDigilabDao.getSearchDigilab(search)
    // digilab

    // search digilab
    fun getSearchDigilab(): Flow<List<SearchDigilabEntity>> = mSearchDigilabDao.getSearchDigilab()
    suspend fun insertSearchDigilab(searchDigilab : List<SearchDigilabEntity>) = mSearchDigilabDao.insertAndDeleteSearchDigilab(searchDigilab)
    suspend fun deleteSearchDigilab() = mSearchDigilabDao.deleteSearchDigilab()
    // search digilab

    // digilab comment
    fun getDigilabComment(): Flow<List<DigilabCommentEntity>> = mDigilabCommentDao.getDigilabComment()
    suspend fun insertDigilabComment(digilabComment : List<DigilabCommentEntity>) = mDigilabCommentDao.insertAndDeleteDigilabComment(digilabComment)
    suspend fun deleteDigilabComment() = mDigilabCommentDao.deleteDigilabComment()
    // digilab comment
    // --------------------------------------- Digilab ----------------------------------------------


    // --------------------------------------- Multimedia ----------------------------------------------
    // multimedia
    fun getMultimedia(): Flow<List<MultimediaEntity>> = mMultimediaDao.getMultimedia()
    suspend fun insertMultimedia(multimedia : List<MultimediaEntity>) = mMultimediaDao.insertAndDeleteMultimedia(multimedia)

    suspend fun deleteMultimedia() = mMultimediaDao.deleteMultimedia()

//    fun getSearchMultimedia(search: String): Flow<List<MultimediaEntity>> = mMultimediaDao.getSearchMultimedia(search)
    // multimedia

    // search multimedia
    fun getSearchMultimedia(): Flow<List<SearchMultimediaEntity>> = mSearchMultimediaDao.getSearchMultimedia()
    suspend fun insertSearchMultimedia(searchMultimedia : List<SearchMultimediaEntity>) = mSearchMultimediaDao.insertAndDeleteSearchMultimedia(searchMultimedia)
    suspend fun deleteSearchMultimedia() = mSearchMultimediaDao.deleteSearchMultimedia()
    // search multimedia

    // digilab comment
    fun getMultimediaComment(): Flow<List<MultimediaCommentEntity>> = mMultimediaCommentDao.getMultimediaComment()
    suspend fun insertMultimediaComment(multimediaComment : List<MultimediaCommentEntity>) =
        mMultimediaCommentDao.insertAndDeleteMultimediaComment(multimediaComment)
    suspend fun deleteMultimediaComment() = mMultimediaCommentDao.deleteMultimediaComment()
    // digilab comment
    // --------------------------------------- Multimedia ----------------------------------------------



    // inbox
    fun getInbox(): Flow<List<InboxEntity>> = mInboxDao.getInbox()
    suspend fun insertInbox(inbox : List<InboxEntity>) = mInboxDao.insertAndDeleteInbox(inbox)

    suspend fun deleteInbox() = mInboxDao.deleteInbox()

    fun getSearchInbox(search: String): Flow<List<InboxEntity>> = mInboxDao.getSearchInbox(search)
    // inbox

}