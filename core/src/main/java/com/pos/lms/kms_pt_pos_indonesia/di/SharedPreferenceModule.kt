package com.pos.lms.kms_pt_pos_indonesia.di

import android.content.Context
import com.pos.lms.kms_pt_pos_indonesia.utils.PreferenceEntity
import com.pos.lms.kms_pt_pos_indonesia.utils.UserPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class SharedPreferenceModule {

    @Singleton
    @Provides
    fun preference (@ApplicationContext context: Context) : PreferenceEntity {
        return UserPreference(context).getPref()
    }
}