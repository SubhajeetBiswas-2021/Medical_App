package com.subhajeet.medical.di

import androidx.compose.ui.tooling.preview.Preview
import com.subhajeet.medical.Utils.BASE_URL
import com.subhajeet.medical.models.ApiServices
import com.subhajeet.medical.repo.Repo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object DiModules {

    @Provides
    fun provideRetrofit():Retrofit{
        return   Retrofit.Builder().baseUrl(BASE_URL).client(
            OkHttpClient.Builder().build()
        ).addConverterFactory(
            GsonConverterFactory.create()).build()
    }


    @Provides
    fun provideApiService(retrofit: Retrofit):ApiServices{
        return retrofit.create(ApiServices::class.java)
    }

    @Provides
    fun provideRepo(apiServices: ApiServices): Repo {
        return Repo(apiServices)
    }
}