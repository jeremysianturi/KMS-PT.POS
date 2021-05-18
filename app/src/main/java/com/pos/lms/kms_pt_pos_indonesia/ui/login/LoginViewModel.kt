package com.pos.lms.kms_pt_pos_indonesia.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.LoginPost
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.login.LoginUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi

class LoginViewModel @ViewModelInject constructor(private val loginUsecase: LoginUsecase) : ViewModel() {

    fun getLogin(loginPost: LoginPost) = loginUsecase.login(loginPost).asLiveData()

//    val getParId = loginUsecase.getParid().asLiveData()

    fun getParId(token: String) = loginUsecase.getParid(token).asLiveData()

}