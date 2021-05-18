package com.pos.lms.kms_pt_pos_indonesia.domain.usecase.wahana.wahanaviewers

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.repository.WahanaViewersRepository
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana
import com.pos.lms.kms_pt_pos_indonesia.domain.model.WahanaViewers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WahanaViewersInteractor @Inject constructor(private val wahanaViewersRepository : WahanaViewersRepository) :
    WahanaViewersUsecase {

    override fun getWahanaViewers(idKnowledge: String): Flow<Resource<List<WahanaViewers>>> =
        wahanaViewersRepository.getWahanaViewers(idKnowledge)

}