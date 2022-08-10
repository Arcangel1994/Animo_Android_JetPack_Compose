package com.example.ktor.data.repository

import androidx.paging.PagingData
import com.example.ktor.domain.model.Hero
import com.example.ktor.domain.repository.DataStoreOperations
import com.example.ktor.domain.repository.LocalDataSource
import com.example.ktor.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remote: RemoteDataSource,
    private val dataStore: DataStoreOperations,
    private val local: LocalDataSource
){

    suspend fun saveOnBoardingState(completed: Boolean){
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }

    fun getAllHeroes(): Flow<PagingData<Hero>> {
        return remote.getAllHeroes()
    }

    fun searchHeros(query: String): Flow<PagingData<Hero>> {
        return remote.searchHeroes(query = query)
    }

    suspend fun getSelectedHero(heroId: Int): Hero{
        return local.getSelectedHero(heroId = heroId)
    }

}