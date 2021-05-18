package com.pos.lms.kms_pt_pos_indonesia.domain.repository

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana
import com.pos.lms.kms_pt_pos_indonesia.domain.model.WahanaViewers
import kotlinx.coroutines.flow.Flow

interface IWahanaViewersRepository {

    fun getWahanaViewers(idKnowledge: String) : Flow<Resource<List<WahanaViewers>>>


}