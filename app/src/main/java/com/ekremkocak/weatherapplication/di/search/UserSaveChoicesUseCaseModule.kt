package com.ekremkocak.weatherapplication.di.search

import com.ekremkocak.weatherapplication.data.rp.savechoicerejection.UserSaveChoiceRejectionRepository
import com.ekremkocak.weatherapplication.use_case.search.SaveUserChoicesUSeCase
import com.ekremkocak.weatherapplication.use_case.search.SaveUserChoicesUseCaseImpl
import com.sporyap.sporyap.data.rp.event.acceptancerejection.UserSaveChoiceRejectionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UserSaveChoicesUseCaseModule {
    @Binds
    @ViewModelScoped
    abstract fun bindEventParticipationUseCase(useCaseModule: SaveUserChoicesUseCaseImpl) : SaveUserChoicesUSeCase
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class UserSaveChoicesUseCaseModuleRepo {
    @Binds
    @ViewModelScoped
    abstract fun bindEventParticipationUseCase(useCaseModule: UserSaveChoiceRejectionRepositoryImpl) : UserSaveChoiceRejectionRepository
}