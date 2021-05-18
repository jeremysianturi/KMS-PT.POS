package com.pos.lms.kms_pt_pos_indonesia.domain.repository

import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.WahanaCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.wahana.WahanaResponse
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Submit
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana
import kotlinx.coroutines.flow.Flow

interface IWahanaRepository {

    fun getWahana(type: String) : Flow<Resource<List<Wahana>>>

//    fun getSearchWahana(search: String): Flow<List<Wahana>>
}