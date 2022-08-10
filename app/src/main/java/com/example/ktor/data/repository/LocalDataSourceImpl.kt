package com.example.ktor.data.repository

import com.example.ktor.data.local.BorutoDatabase
import com.example.ktor.domain.model.Hero
import com.example.ktor.domain.repository.LocalDataSource

class LocalDataSourceImpl(borutoDatabase: BorutoDatabase): LocalDataSource {

    private val heroDao = borutoDatabase.heroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId = heroId)
    }

}