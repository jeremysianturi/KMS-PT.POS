package com.pos.lms.kms_pt_pos_indonesia.data.repository

import com.pos.lms.kms_pt_pos_indonesia.data.NetworkBoundResourceWithDeleteLocalData
import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.LocalDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.RemoteDataSource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.network.ApiResponse
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.articlecategory.CategoryResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Category
import com.pos.lms.kms_pt_pos_indonesia.domain.repository.ICategoryRepository
import com.pos.lms.kms_pt_pos_indonesia.helper.datamapper.DataMapperCategory
import com.pos.lms.kms_pt_pos_indonesia.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ICategoryRepository {

    override fun getCategory(beginDate: String, endDate: String): Flow<Resource<List<Category>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<Category>, List<CategoryResponse>>() {

            override fun loadFromDB(): Flow<List<Category>> {
                return localDataSource.getCategory().map {
                    DataMapperCategory.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<Category>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<CategoryResponse>>> =
                remoteDataSource.getCategory(beginDate, endDate)

            override suspend fun saveCallResult(data: List<CategoryResponse>) {
                val list = DataMapperCategory.mapResponsetoEntities(data)
                localDataSource.insertCategory(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteCategory()
            }

        }.asFlow()

}