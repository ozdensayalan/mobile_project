package com.example.yemeksiparisi.di

import com.example.yemeksiparisi.data.datasource.YemeklerDataSource
import com.example.yemeksiparisi.data.repo.YemeklerRepository
import com.example.yemeksiparisi.retrofit.ApiUtils
import com.example.yemeksiparisi.retrofit.YemeklerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideKisilerRepository(yds: YemeklerDataSource) : YemeklerRepository {
        return YemeklerRepository(yds)
    }


    @Provides
    @Singleton
    fun provideKisilerDataSource(kdao:YemeklerDao) : YemeklerDataSource {
        return YemeklerDataSource(kdao)
    }
    @Provides
    @Singleton
    fun provideKisilerDao() : YemeklerDao {
        return ApiUtils.getYemeklerDao()
    }
}