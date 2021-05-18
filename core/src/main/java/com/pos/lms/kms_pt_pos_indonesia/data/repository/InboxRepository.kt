package com.pos.lms.kms_pt_pos_indonesia.data.repository

import com.pos.lms.kms_pt_pos_indonesia.data.NetworkBoundResourceWithDeleteLocalData
import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.LocalDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.RemoteDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.network.ApiResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.inbox.InboxResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Inbox
import com.pos.lms.kms_pt_pos_indonesia.domain.repository.IInboxRepository
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperInbox
import com.pos.lms.kms_pt_pos_indonesia.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InboxRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IInboxRepository {

    override fun getInbox(): Flow<Resource<List<Inbox>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<Inbox>, List<InboxResponse>>() {

            override fun loadFromDB(): Flow<List<Inbox>> {
                return localDataSource.getInbox().map {
                    DataMapperInbox.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<Inbox>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<InboxResponse>>> =
                remoteDataSource.getInbox()

            override suspend fun saveCallResult(data: List<InboxResponse>) {
                val list = DataMapperInbox.mapResponsetoEntities(data)
                localDataSource.insertInbox(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteInbox()
            }

        }.asFlow()

}