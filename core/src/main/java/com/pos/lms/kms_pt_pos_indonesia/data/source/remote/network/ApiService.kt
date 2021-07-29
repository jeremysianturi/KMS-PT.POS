package com.pos.lms.kms_pt_pos_indonesia.data.source.remote.network

import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.DigilabCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.LoginPost
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.MultimediaCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.WahanaCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.LoginResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.SubmitResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.articlecategory.ListCategoryResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.digilab.ListDigilabResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.digilab.digilabcomment.ListDigilabCommentResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.digilab.searchdigilab.ListSearchDigilabResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.inbox.ListInboxResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.multimedia.ListMultimediaResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.multimedia.multimediacomment.ListMultimediaCommentResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.multimedia.searchmultimedia.ListSearchMultimediaResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.parId.ItemParId
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.ListWahanaResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.searchwahana.ListSearchWahanaResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.wahanacomment.ListWahanaCommentResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.wahanaviewers.ListWahanaViewersResponse
import retrofit2.http.*

interface ApiService {

    // login
    @POST("ldap/api/auth/login")
    @Headers("Content-Type: application/json;charset=UTF-8")
    suspend fun login(
        @Body loginPost: LoginPost
    ): LoginResponse

    // parId
    @GET("lms/api/account?type[]=PARID")
    suspend fun getParId(): List<ItemParId>

    // change pass
    @PUT("lms/api/account")
    suspend fun postChangePassword(
        @Query("username") username: String,
        @Query("password") password: String,
    ): SubmitResponse
    // login


    // --------------------------------------- WAHANA ----------------------------------------------
    // wahana
    @GET("kmsbe/api/home?per_page=100")
    suspend fun getWahana(
        @Query("type") type: String
    ): ListWahanaResponse
    // wahana

    // search wahana
    @GET("kmsbe/api/search")
    suspend fun getSearchWahana(
        @Query("type") type: String,
        @Query("q") search: String,
        @Query("category") category: String
    ): ListSearchWahanaResponse
    // search wahana

    // wahana comment
    @GET("kmsbe/api/comment")
    suspend fun getWahanaComment(
        @Query("order[CHGDT]") order: String,
        @Query("knowledge") knowledgeWahana: Int,
        @Query("begin_date_lte") beginDate: String,
        @Query("end_date_gte") endDate: String
    ): ListWahanaCommentResponse
    // wahana comment

    // create wahana comment
    @POST("kmsbe/api/comment")
    suspend fun createWahanaComment(
        @Body wahanaCommentCreate: WahanaCommentCreate
    ): SubmitResponse
    // create wahana comment

    // wahana viewers
    @GET("kmsbe/api/views")
    suspend fun getWahanaViewers(
        @Query("id_knowledge") idKnowledge: String
    ): ListWahanaViewersResponse
    // wahana viewers

    // --------------------------------------- WAHANA ----------------------------------------------


    // --------------------------------------- DIGILAB ----------------------------------------------
    // digilab
    @GET("kmsbe/api/home?per_page=100")
    suspend fun getDigilab(
        @Query("type") type: String
    ): ListDigilabResponse
    // digilab

    // search digilab
    @GET("kmsbe/api/search")
    suspend fun getSearchDigilab(
        @Query("type") type: String,
        @Query("q") search: String,
        @Query("category") category: String
    ): ListSearchDigilabResponse
    // search digilab

    // digilab comment
    @GET("kmsbe/api/comment")
    suspend fun getDigilabComment(
        @Query("order[CHGDT]") order: String,
        @Query("knowledge") knowledgeDigilab: Int,
        @Query("begin_date_lte") beginDate: String,
        @Query("end_date_gte") endDate: String
    ): ListDigilabCommentResponse
    // digilab comment

    // create digilab comment
    @POST("kmsbe/api/comment")
    suspend fun createDigilabComment(
        @Body digilabCommentCreate: DigilabCommentCreate
    ): SubmitResponse
    // create digilab comment
    // --------------------------------------- DIGILAB ----------------------------------------------


    // --------------------------------------- MULTIMEDIA ----------------------------------------------
    // multimedia
    @GET("kmsbe/api/home?per_page=100")
    suspend fun getMultimedia(
        @Query("type") type: String
    ): ListMultimediaResponse
    // multimedia

    // search multimedia
    @GET("kmsbe/api/search")
    suspend fun getSearchMultimedia(
        @Query("type") type: String,
        @Query("q") search: String
    ): ListSearchMultimediaResponse
    // search multimedia

    // multimedia comment
    @GET("kmsbe/api/comment")
    suspend fun getMultimediaComment(
        @Query("order[CHGDT]") order: String,
        @Query("knowledge") knowledgeMultimedia: Int,
        @Query("begin_date_lte") beginDate: String,
        @Query("end_date_gte") endDate: String
    ): ListMultimediaCommentResponse
    // multimedia comment

    // create multimedia comment
    @POST("kmsbe/api/comment")
    suspend fun createMultimediaComment(
        @Body multimediaCommentCreate: MultimediaCommentCreate
    ): SubmitResponse
    // create multimedia comment
    // --------------------------------------- MULTIMEDIA ----------------------------------------------


    // --------------------------------------- INBOX ----------------------------------------------
    // inbox
    @GET("kmsbe/api/notif")
    suspend fun getInbox(
    ): ListInboxResponse
    // inbox
    // --------------------------------------- INBOX ----------------------------------------------


    // --------------------------------------- CATEGORY ----------------------------------------------
    // get category
    @GET("ldap/api/objects?include[]=business_code&order[BEGDA]=asc&object_type[]=CTCTN&per_page=999")
    suspend fun getCategory(
        @Query("begin_date_lte") beginDate: String,
        @Query("end_date_gte") endDate: String,
        ): ListCategoryResponse
    // get category
    // --------------------------------------- CATEGORY ----------------------------------------------
}