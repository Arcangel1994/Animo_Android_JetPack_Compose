package com.example.ktor.domain.use_cases

import com.example.ktor.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.example.ktor.domain.use_cases.get_selected_hero.GetSelectedHeroUseCase
import com.example.ktor.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.example.ktor.domain.use_cases.save_onbloarding.SaveOnBoardingUseCase
import com.example.ktor.domain.use_cases.search_heroes.SearchHeroesUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val searchHeroesUseCase: SearchHeroesUseCase,
    val getSelectedHeroUseCase: GetSelectedHeroUseCase
)
