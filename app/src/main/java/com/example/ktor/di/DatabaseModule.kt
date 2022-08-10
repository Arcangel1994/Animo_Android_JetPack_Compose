package com.example.ktor.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ktor.data.local.BorutoDatabase
import com.example.ktor.data.repository.LocalDataSourceImpl
import com.example.ktor.domain.repository.LocalDataSource
import com.example.ktor.util.Constants.BORUTO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BorutoDatabase{
        return Room.databaseBuilder(context, BorutoDatabase::class.java, BORUTO_DATABASE).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(borutoDatabase: BorutoDatabase): LocalDataSource{
        return LocalDataSourceImpl(borutoDatabase = borutoDatabase)
    }

}