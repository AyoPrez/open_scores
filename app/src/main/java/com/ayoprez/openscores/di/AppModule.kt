package com.ayoprez.openscores.di

import com.ayoprez.openscores.OpenScoresApp
import com.ayoprez.openscores.data.remote.BasketApi
import com.ayoprez.openscores.repository.BasketballRepository
import com.ayoprez.openscores.repository.BasketballRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBasketApi(): BasketApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(OpenScoresApp().getBaseBasketballUrl())
            .build()
            .create(BasketApi::class.java)
    }

    @Singleton
    @Provides
    fun provideBasketballRepository(api: BasketApi):BasketballRepository = BasketballRepositoryImpl(api)

}