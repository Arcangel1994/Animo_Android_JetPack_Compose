package com.example.ktor.domain.use_cases.get_selected_hero

import com.example.ktor.data.repository.Repository
import com.example.ktor.domain.model.Hero

class GetSelectedHeroUseCase(private val repository: Repository) {

    suspend operator fun invoke(heroId: Int): Hero {
        return repository.getSelectedHero(heroId = heroId)
    }

}