package com.pos.lms.kms_pt_pos_indonesia.domain.usecase.login

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.LoginPost
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Login
import com.pos.lms.kms_pt_pos_indonesia.domain.model.ParId
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Submit
import com.pos.lms.kms_pt_pos_indonesia.domain.repository.ILoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginInteractor @Inject constructor(private val loginRepository: ILoginRepository) : LoginUsecase {

    override fun login(loginPost: LoginPost): Flow<Resource<Login>> =
        loginRepository.login(loginPost)

    override fun getParid(token : String): Flow<Resource<List<ParId>>> =
        loginRepository.getParId(token)

    override fun changePassword(username: String, password: String): Flow<Resource<Submit>> =
        loginRepository.changePassword(username, password)

}