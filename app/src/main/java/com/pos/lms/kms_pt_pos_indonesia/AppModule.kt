package com.pos.lms.kms_pt_pos_indonesia

import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.category.CategoryInteractor
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.category.CategoryUsecase
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.digilab.DigilabInteractor
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.digilab.DigilabUsecase
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.digilab.digilabcomment.DigilabCommentInteractor
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.digilab.digilabcomment.DigilabCommentUsecase
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.digilab.searchdigilab.SearchDigilabInteractor
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.digilab.searchdigilab.SearchDigilabUsecase
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.inbox.InboxInteractor
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.inbox.InboxUsecase
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.login.LoginInteractor
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.login.LoginUsecase
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.multimedia.MultimediaInteractor
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.multimedia.MultimediaUsecase
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.multimedia.multimediacomment.MultimediaCommentInteractor
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.multimedia.multimediacomment.MultimediaCommentUsecase
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.multimedia.searchmultimedia.SearchMultimediaInteractor
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.multimedia.searchmultimedia.SearchMultimediaUsecase
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.wahana.WahanaInteractor
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.wahana.WahanaUsecase
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.wahana.searchwahana.SearchWahanaInteractor
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.wahana.searchwahana.SearchWahanaUsecase
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.wahana.wahanacomment.WahanaCommentInteractor
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.wahana.wahanacomment.WahanaCommentUsecase
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.wahana.wahanaviewers.WahanaViewersInteractor
import com.pos.lms.kms_pt_pos_indonesia.domain.usecase.wahana.wahanaviewers.WahanaViewersUsecase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideLoginUsecase(loginInteractor: LoginInteractor) : LoginUsecase

    @Binds
    abstract fun provideWahanaUseCase(wahanaInteractor : WahanaInteractor) : WahanaUsecase

    @Binds
    abstract fun provideSearchWahanaUseCase(searchWahanaInteractor : SearchWahanaInteractor) : SearchWahanaUsecase

    @Binds
    abstract fun provideWahanaCommentUseCase(wahanaCommentInteractor : WahanaCommentInteractor) : WahanaCommentUsecase

    @Binds
    abstract fun provideWahanaViewersUseCase(wahanaViewersInteractor : WahanaViewersInteractor) : WahanaViewersUsecase

    @Binds
    abstract fun provideDigilabUseCase(digilabInteractor : DigilabInteractor) : DigilabUsecase

    @Binds
    abstract fun provideSearchDigilabUseCase(searchDigilabInteractor : SearchDigilabInteractor) : SearchDigilabUsecase

    @Binds
    abstract fun provideDigilabCommentUseCase(digilabCommentInteractor : DigilabCommentInteractor) : DigilabCommentUsecase

    @Binds
    abstract fun provideMultimediaUseCase(multimediaInteractor : MultimediaInteractor) : MultimediaUsecase

    @Binds
    abstract fun provideSearchMultimediaUseCase(searchMultimediaInteractor : SearchMultimediaInteractor) : SearchMultimediaUsecase

    @Binds
    abstract fun provideMultimediaCommentUseCase(multimediaCommentInteractor : MultimediaCommentInteractor) : MultimediaCommentUsecase

    @Binds
    abstract fun provideInboxUseCase(inboxInteractor : InboxInteractor) : InboxUsecase

    @Binds
    abstract fun provideCategoryUseCase(categoryInteractor : CategoryInteractor) : CategoryUsecase

}