package com.example.testbri.di

import com.example.testbri.network.RetroServiceInstance
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

    @Provides
    @Singleton
    fun provideClient(): RetroServiceInstance {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://hookb.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(RetroServiceInstance::class.java)
    }

}