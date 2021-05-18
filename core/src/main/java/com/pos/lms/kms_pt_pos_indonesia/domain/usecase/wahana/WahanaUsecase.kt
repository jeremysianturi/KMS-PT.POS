package com.pos.lms.kms_pt_pos_indonesia.domain.usecase.wahana

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.WahanaCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Submit
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana
import kotlinx.coroutines.flow.Flow

interface WahanaUsecase {

    fun getWahana(type: String): Flow<Resource<List<Wahana>>>

//    fun getSearchWahana(search: String): Flow<List<Wahana>>

}