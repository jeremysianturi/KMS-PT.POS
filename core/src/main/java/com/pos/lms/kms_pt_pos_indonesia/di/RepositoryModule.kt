package com.pos.lms.kms_pt_pos_indonesia.di

import com.pos.lms.kms_pt_pos_indonesia.data.repository.LoginRepository
import com.pos.lms.kms_pt_pos_indonesia.domain.repository.ILoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module(includes = [NetworkModule::class])
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(loginRepository : LoginRepository): ILoginRepository

}