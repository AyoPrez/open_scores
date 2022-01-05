package com.ayoprez.openscores.di

import android.app.Application
import androidx.room.Room
import com.ayoprez.openscores.OpenScoresApp
import com.ayoprez.openscores.data.data_source.SportsDB
import com.ayoprez.openscores.data.remote.BasketApi
import com.ayoprez.openscores.data.repositories.BasketballRepositoryImpl
import com.ayoprez.openscores.data.repositories.SportsRepositoryImpl
import com.ayoprez.openscores.domain.repository.BasketballRepository
import com.ayoprez.openscores.domain.repository.SportsRepository
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
    fun provideBasketballRepository(api: BasketApi): BasketballRepository = BasketballRepositoryImpl(api)

    @Singleton
    @Provides
    fun sportsDB(app: Application): SportsDB {
        return Room.databaseBuilder(app, SportsDB::class.java, SportsDB.DATABASE_NAME).build()
    }

    @Singleton
    @Provides
    fun provideSportsRepository(db: SportsDB): SportsRepository = SportsRepositoryImpl(db.sportsDao)

}