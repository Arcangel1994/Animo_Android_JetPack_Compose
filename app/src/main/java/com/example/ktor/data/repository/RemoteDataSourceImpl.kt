package com.example.ktor.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.ktor.data.local.BorutoDatabase
import com.example.ktor.data.local.dao.HeroDao
import com.example.ktor.data.paging_source.HeroRemoteMediator
import com.example.ktor.data.paging_source.SearchHeroesSource
import com.example.ktor.data.remote.BorutoApi
import com.example.ktor.domain.model.Hero
import com.example.ktor.domain.repository.RemoteDataSource
import com.example.ktor.util.Constants.ITEMS_PER_PAGE
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(private val borutoApi: BorutoApi, private val borutoDatabase: BorutoDatabase): RemoteDataSource{

    private val heroDao: HeroDao = borutoDatabase.heroDao()

    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { heroDao.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HeroRemoteMediator(borutoApi = borutoApi, borutoDatabase = borutoDatabase),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchHeroesSource(borutoApi = borutoApi, query = query)
            }
        ).flow
    }
}