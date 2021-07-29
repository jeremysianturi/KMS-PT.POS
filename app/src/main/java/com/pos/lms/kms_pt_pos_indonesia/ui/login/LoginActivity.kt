package com.pos.lms.kms_pt_pos_indonesia.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.github.dhaval2404.form_validation.rule.NonEmptyRule
import com.github.dhaval2404.form_validation.validation.FormValidator
import com.pos.lms.kms_pt_pos_indonesia.R
import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.LoginPost
import com.pos.lms.kms_pt_pos_indonesia.databinding.ActivityLoginBinding
import com.pos.lms.kms_pt_pos_indonesia.helper.Debounce.onThrottledClick
import com.pos.lms.kms_pt_pos_indonesia.ui.home.HomeActivity
import com.pos.lms.kms_pt_pos_indonesia.util.dialog.ErrorBottomSheet
import com.pos.lms.kms_pt_pos_indonesia.utils.ErrorMessageSplit
import com.pos.lms.kms_pt_pos_indonesia.utils.PreferenceEntity
import com.pos.lms.kms_pt_pos_indonesia.utils.UserPreference
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import kotlin.system.exitProcess

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val homeViewModel: LoginViewModel by viewModels()

    private var doubleBackToExitPressedOnce = false

    private lateinit var mPreference: UserPreference
    private lateinit var mPreferenceEntity: PreferenceEntity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mPreference = UserPreference(this)
        mPreferenceEntity = mPreference.getPref()

        val username = mPreferenceEntity.username ?: ""
        val password = mPreferenceEntity.password ?: ""

        if (username != "" || password != "") {
            binding.edtUsernameLogin.setText(username)
            binding.edtPasswordLogin.setText(password)
        }

        if (mPreferenceEntity.isLogin == true) {
            val mIntent = Intent(this, HomeActivity::class.java)
            startActivity(mIntent)
        }

        // onclclick listener
        binding.btnLogin.onThrottledClick {
            if (validationField()) {
                isValidField()
            } else {
                Toast.makeText(this, "Lengkapi data terlebih dahulu !", Toast.LENGTH_SHORT)
                    .show()

            }
        }

    }

    private fun isValidField() {
        // disable button

        val username = binding.edtUsernameLogin.text.toString()
        val password = binding.edtPasswordLogin.text.toString()

        val loginPost = LoginPost(
            applicationId = "2",
            username = username,
            password = password
        )


        homeViewModel.getLogin(loginPost).observe(this, { login ->
            if (login != null) {
                when (login) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE

                        mPreferenceEntity.token = login.data?.accessToken
//                        mPreferenceEntity.token =
//                            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJ5aXQwR05nbVVHamIyZXRxIiwiaWF0IjoxNjEzMzYzMjIyLCJuYmYiOjE2MTMzNjMyMjIsImV4cCI6MTYxMzQ0OTYyMiwidXNlciI6Ijk5MjQxNTIxNCIsInJvbGUiOlsiRU1QUE9TIiwiUEFSVElDSVBBTlQiXX0.1MF-boejJ28IQB2m5tOZS2ouhJg2OaoBjyxs4VpP_cs"
                        mPreferenceEntity.tokenType = login.data?.tokenType.toString()
                        mPreferenceEntity.username = username
                        mPreferenceEntity.password = password
                        mPreferenceEntity.tokenType = login.data?.tokenType ?: "Bearer"
                        mPreferenceEntity.isLogin = true

                        mPreference.setPref(mPreferenceEntity)

//                        observerParId()
                        val mIntent = Intent(this, HomeActivity::class.java)
                        startActivity(mIntent)

                        Timber.d("ParId Success")
                        Timber.tag("LoginEmtity")
                            .d("ParIdLoginActivity : ${mPreferenceEntity.parId}")
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        val message = ErrorMessageSplit.message(login.message.toString())
                        val code = ErrorMessageSplit.code(login.message.toString())
                        ErrorBottomSheet.instance(code, message).show(supportFragmentManager, ErrorBottomSheet.TAG)
//                        val message = ErrorMessageSplit.message(login.message.toString())
//                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun validationField(): Boolean {
        return FormValidator.getInstance()
            .addField(
                binding.TilUsername,
                NonEmptyRule(getString(R.string.ERROR_FIELD_REQUIRED))
            )
            .addField(
                binding.TilPassword,
                NonEmptyRule(getString(R.string.ERROR_FIELD_REQUIRED))
            )
            .validate()

    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
            exitProcess(0)
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Klik sekali lagi untuk keluar", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(
            { doubleBackToExitPressedOnce = false },
            2000
        ) // review
    }


    override fun onDestroy() {
        super.onDestroy()
        val loginPost = LoginPost(
            applicationId = "2",
            username = "username",
            password = "password"
        )

        homeViewModel.getParId("").removeObservers(this)
        homeViewModel.getLogin(loginPost).removeObservers(this)
        Timber.tag("LoginDestroy").e("removeObserver")
    }
}