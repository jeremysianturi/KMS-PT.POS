package com.pos.lms.kms_pt_pos_indonesia.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pos.lms.kms_pt_pos_indonesia.R
import com.pos.lms.kms_pt_pos_indonesia.databinding.ActivitySplashscreenBinding
import com.pos.lms.kms_pt_pos_indonesia.ui.home.HomeActivity
import com.pos.lms.kms_pt_pos_indonesia.ui.login.LoginActivity
import com.pos.lms.kms_pt_pos_indonesia.util.CheckConnection
import com.pos.lms.kms_pt_pos_indonesia.utils.PreferenceEntity
import com.pos.lms.kms_pt_pos_indonesia.utils.UserPreference
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashscreenBinding

    private var mPreference: UserPreference? = null
    private val mPreferenceEntity: PreferenceEntity get() = mPreference!!.getPref()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mPreference = UserPreference(this)

        // animation
        val rightToLeftAnimation =
            android.view.animation.AnimationUtils.loadAnimation(this, R.anim.right_animation)

        binding.imageView8.animation = rightToLeftAnimation

        val checkConnection = CheckConnection.internetAvailable(this)
        if (!checkConnection) {
            Toast.makeText(this, "No Connection Detected", Toast.LENGTH_SHORT).show()
        } else {
            Timber.d("checkConnectionClass : $checkConnection")
        }

        val splashTread: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(2000)
                } catch (e: InterruptedException) {
                    // do nothing
                } finally {
//                    val mIntent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
//                    startActivity(mIntent)
                    if (mPreferenceEntity.isLogin == true) {
                        val mIntent = Intent(this@SplashScreen, HomeActivity::class.java)
//                        val i = Intent(this, HomeActivity::class.java)
////                        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
////                        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(mIntent)
//                        finish()
                    } else {
                        val mIntent = Intent(this@SplashScreen, LoginActivity::class.java)
                        startActivity(mIntent)
                        finish()
                    }
                }

            }

        }

        splashTread.start()

    }
}