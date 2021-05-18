package com.pos.lms.kms_pt_pos_indonesia.domain.usecase.login

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.LoginPost
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Login
import com.pos.lms.kms_pt_pos_indonesia.domain.model.ParId
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Submit
import kotlinx.coroutines.flow.Flow

interface LoginUsecase {

    fun login(loginPost: LoginPost): Flow<Resource<Login>>
    fun getParid(token: String): Flow<Resource<List<ParId>>>
    fun changePassword(username: String, password: String): Flow<Resource<Submit>>

}