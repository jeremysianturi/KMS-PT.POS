package com.pos.lms.kms_pt_pos_indonesia.di

import android.content.Context
import androidx.room.Room
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.KmsDatabase
import com.pos.lms.kms_pt_pos_indonesia.data.source.local.room.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): KmsDatabase = Room.databaseBuilder(
        context,
        KmsDatabase::class.java, "LMS.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideLoginDao(database: KmsDatabase) : LoginDao = database.loginDao()

    @Provides
    fun provideSubmitDao(database : KmsDatabase) : SubmitDao = database.submit()

    @Provides
    fun provideWahanaDao(database: KmsDatabase): WahanaDao = database.wahanaDao()

    @Provides
    fun provideSearchWahanaDao(database: KmsDatabase): SearchWahanaDao = database.searchWahanaDao()

    @Provides
    fun provideWahanaCommentDao(database: KmsDatabase): WahanaCommentDao = database.wahanaCommentDao()

    @Provides
    fun provideWahanaViewersDao(database: KmsDatabase): WahanaViewersDao = database.wahanaViewersDao()

    @Provides
    fun provideDigilabDao(database: KmsDatabase): DigilabDao = database.digilabDao()

    @Provides
    fun provideSearchDigilabDao(database: KmsDatabase): SearchDigilabDao = database.searchDigilabDao()

    @Provides
    fun provideDigilabCommentDao(database: KmsDatabase): DigilabCommentDao = database.digilabCommentDao()

    @Provides
    fun provideMultimediaDao(database: KmsDatabase): MultimediaDao = database.multimediaDao()

    @Provides
    fun provideSearchMultimediaDao(database: KmsDatabase): SearchMultimediaDao = database.searchMultimediaDao()

    @Provides
    fun provideMultimediaCommentDao(database: KmsDatabase): MultimediaCommentDao = database.multimediaCommentDao()

    @Provides
    fun provideInboxDao(database: KmsDatabase): InboxDao = database.inboxDao()

    @Provides
    fun provideCategoryDao(database: KmsDatabase): CategoryDao = database.categoryDao()

}