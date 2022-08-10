package com.example.ktor.domain.use_cases.search_heroes

import androidx.paging.PagingData
import com.example.ktor.data.repository.Repository
import com.example.ktor.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(private val repository: Repository) {

    operator fun invoke (query: String): Flow<PagingData<Hero>>{
        return repository.searchHeros(query = query)
    }

}