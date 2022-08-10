package com.example.ktor.di

import android.content.Context
import com.example.ktor.data.repository.DataStoreOperationsImpl
import com.example.ktor.data.repository.Repository
import com.example.ktor.domain.repository.DataStoreOperations
import com.example.ktor.domain.use_cases.UseCases
import com.example.ktor.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.example.ktor.domain.use_cases.get_selected_hero.GetSelectedHeroUseCase
import com.example.ktor.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.example.ktor.domain.use_cases.save_onbloarding.SaveOnBoardingUseCase
import com.example.ktor.domain.use_cases.search_heroes.SearchHeroesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RespositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(@ApplicationContext context: Context): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository = repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository = repository),
            getAllHeroesUseCase = GetAllHeroesUseCase(repository = repository),
            searchHeroesUseCase = SearchHeroesUseCase(repository = repository),
            getSelectedHeroUseCase = GetSelectedHeroUseCase(repository = repository)
        )
    }

}