package com.pos.lms.kms_pt_pos_indonesia.data.repository

import com.pos.lms.kms_pt_pos_indonesia.data.NetworkBoundResource
import com.pos.lms.kms_pt_pos_indonesia.data.NetworkBoundResourceWithDeleteLocalData
import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.LocalDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.network.ApiResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.RemoteDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.LoginPost
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.LoginResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.SubmitResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.parId.ItemParId
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Login
import com.pos.lms.kms_pt_pos_indonesia.domain.model.ParId
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Submit
import com.pos.lms.kms_pt_pos_indonesia.domain.repository.ILoginRepository
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperLogin
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperParId
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperSubmit
import com.pos.lms.kms_pt_pos_indonesia.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :  ILoginRepository {

    override fun login(loginPost: LoginPost): Flow<Resource<Login>> =
        object : NetworkBoundResource<Login, LoginResponse>() {
            override fun loadFromDB(): Flow<Login> {
                return localDataSource.getLogin().map {
                    DataMapperLogin.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: Login?): Boolean =
                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<LoginResponse>> =
                remoteDataSource.getLogin(loginPost)

            override suspend fun saveCallResult(data: LoginResponse) {
                val tourismList = DataMapperLogin.mapResponsetoEntities(data)
                localDataSource.insertLogin(tourismList)
            }
        }.asFlow()


    override fun getParId(token: String): Flow<Resource<List<ParId>>> =
        object : NetworkBoundResource<List<ParId>, List<ItemParId>>() {
            override fun loadFromDB(): Flow<List<ParId>> {
                return localDataSource.getParId().map {
                    DataMapperParId.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<ParId>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<ItemParId>>> =
                remoteDataSource.getParId(token)


            override suspend fun saveCallResult(data: List<ItemParId>) {
                val list = DataMapperParId.mapResponsesToEntities(data)
                localDataSource.insertParId(list)
            }

        }.asFlow()


    override fun changePassword(username: String, password: String): Flow<Resource<Submit>> =
        object : NetworkBoundResourceWithDeleteLocalData<Submit, SubmitResponse>() {

            override fun loadFromDB(): Flow<Submit> {
                return localDataSource.getSubmitResponse().map {
                    DataMapperSubmit.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: Submit?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<SubmitResponse>> =
                remoteDataSource.postChangePassword(username, password)

            override suspend fun saveCallResult(data: SubmitResponse) {
                val list = DataMapperSubmit.mapResponsetoEntities(data)
                localDataSource.insertSubmitResponse(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteSubmit()
            }

        }.asFlow()

}