package com.pos.lms.kms_pt_pos_indonesia.data.source.remote

import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.network.ApiResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.network.ApiService
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.DigilabCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.LoginPost
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.MultimediaCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.WahanaCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.LoginResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.SubmitResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.articlecategory.CategoryResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.digilab.DigilabResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.digilab.digilabcomment.DigilabCommentResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.digilab.searchdigilab.SearchDigilabResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.inbox.InboxResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.multimedia.MultimediaResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.multimedia.multimediacomment.MultimediaCommentResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.multimedia.searchmultimedia.SearchMultimediaResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.parId.ItemParId
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.WahanaResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.searchwahana.SearchWahanaResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.wahanacomment.WahanaCommentResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.wahanaviewers.WahanaViewersResponse
import com.pos.lms.kms_pt_pos_indonesia.utils.ApiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService : ApiService
    ) {

    private val tag = RemoteDataSource::class.java.simpleName.toString()

    // --------------------------------------- login ----------------------------------------------
    suspend fun getLogin(loginPost: LoginPost): Flow<ApiResponse<LoginResponse>> {
        //get data from remote APi
        return flow {
            try {
                val response = apiService.login(loginPost)
                val data = response.accessToken
                if (data.isEmpty()) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: ApiException) {
//                val errorCode = e.toString().replace(regex = Regex("[^0-9]"), replacement = "")
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    }

    suspend fun getParId(token: String): Flow<ApiResponse<List<ItemParId>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getParId()
                val dataArray = response[0].accessToken
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: ApiException) {
                emit(ApiResponse.Error(e.toString()))
//                Timber.e("RemoteDataSource", e.toString())
                Timber.tag("RemoteDataSource").e(e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    // --------------------------------------- change password ----------------------------------------------
    suspend fun postChangePassword(
        username: String,
        password: String
    ): Flow<ApiResponse<SubmitResponse>> {
        return flow {
            try {
                val response = apiService.postChangePassword(username, password)
                val dataArray = response.status
                if (dataArray) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }


    // --------------------------------------- Wahana ----------------------------------------------
    suspend fun getWahana(
        type : String
    ): Flow<ApiResponse<List<WahanaResponse>>> {
        return flow {
            try {
                val response = apiService.getWahana(type)
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSearchWahana(
        type : String,
        search : String,
        category : String
    ): Flow<ApiResponse<List<SearchWahanaResponse>>> {
        return flow {
            try {
                val response = apiService.getSearchWahana(type,search,category)
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getWahanaComment(
        order : String,
        knowledgeWahana : Int,
        beginDate : String,
        endDate : String
    ): Flow<ApiResponse<List<WahanaCommentResponse>>> {
        return flow {
            try {
                val response = apiService.getWahanaComment(order,knowledgeWahana,beginDate,endDate)
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun createWahanaComment(
        wahanaCommentCreate: WahanaCommentCreate
    ): Flow<ApiResponse<SubmitResponse>> {
        return flow {
            try {
                val response = apiService.createWahanaComment(wahanaCommentCreate)
                val status = response.status
                if (status) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getWahanaViewers(
        idKnowledge : String
    ): Flow<ApiResponse<List<WahanaViewersResponse>>> {
        return flow {
            try {
                val response = apiService.getWahanaViewers(idKnowledge)
                val dataArray = response.views
                Timber.d("check value viewers : $dataArray")
                if (!dataArray.toString().equals("")) {
                    emit(ApiResponse.Success(response.views))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }


    // --------------------------------------- Digilab ----------------------------------------------
    suspend fun getDigilab(type: String): Flow<ApiResponse<List<DigilabResponse>>> {
        return flow {
            try {
                val response = apiService.getDigilab(type)
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSearchDigilab(
        type : String,
        search : String,
        category: String
    ): Flow<ApiResponse<List<SearchDigilabResponse>>> {
        return flow {
            try {
                val response = apiService.getSearchDigilab(type,search,category)
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDigilabComment(
        order : String,
        knowledgeDigilab : Int,
        beginDate : String,
        endDate : String
    ): Flow<ApiResponse<List<DigilabCommentResponse>>> {
        return flow {
            try {
                val response = apiService.getDigilabComment(order,knowledgeDigilab,beginDate,endDate)
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun createDigilabComment(
        digilabCommentCreate: DigilabCommentCreate
    ): Flow<ApiResponse<SubmitResponse>> {
        return flow {
            try {
                val response = apiService.createDigilabComment(digilabCommentCreate)
                val status = response.status
                if (status) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }


    // --------------------------------------- Multimedia ----------------------------------------------
    suspend fun getMultimedia(type: String): Flow<ApiResponse<List<MultimediaResponse>>> {
        return flow {
            try {
                val response = apiService.getMultimedia(type)
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSearchMultimedia(
        type : String,
        search : String
    ): Flow<ApiResponse<List<SearchMultimediaResponse>>> {
        return flow {
            try {
                val response = apiService.getSearchMultimedia(type,search)
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMultimediaComment(
        order : String,
        knowledgeMultimedia : Int,
        beginDate : String,
        endDate : String
    ): Flow<ApiResponse<List<MultimediaCommentResponse>>> {
        return flow {
            try {
                val response = apiService.getMultimediaComment(order,knowledgeMultimedia,beginDate,endDate)
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun createMultimediaComment(
        multimediaCommentCreate: MultimediaCommentCreate
    ): Flow<ApiResponse<SubmitResponse>> {
        return flow {
            try {
                val response = apiService.createMultimediaComment(multimediaCommentCreate)
                val status = response.status
                if (status) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }


    // --------------------------------------- Inbox ----------------------------------------------
    suspend fun getInbox(): Flow<ApiResponse<List<InboxResponse>>> {
        return flow {
            try {
                val response = apiService.getInbox()
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }


    // --------------------------------------- Category ----------------------------------------------
    suspend fun getCategory(
        beginDate: String,
        endDate: String
    ): Flow<ApiResponse<List<CategoryResponse>>> {
        return flow {
            try {
                val response = apiService.getCategory(beginDate,endDate)
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}