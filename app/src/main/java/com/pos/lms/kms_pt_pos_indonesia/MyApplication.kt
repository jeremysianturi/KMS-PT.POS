package com.pos.lms.kms_pt_pos_indonesia

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Muhammad Zaim Milzam on 19/03/21.
 * linkedin : Muhammad Zaim Milzam
 */
@HiltAndroidApp
open class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}