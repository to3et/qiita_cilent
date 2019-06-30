package com.sample.qiitaclient.dagger

import android.content.Context
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sample.qiitaclient.client.ArticleClient
import com.sample.qiitaclient.db.ArticleDao
import com.sample.qiitaclient.db.QiitaDb
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ClientModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://qiita.com")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    @Singleton
    fun provideArticleClient(retrofit: Retrofit): ArticleClient =
        retrofit.create(ArticleClient::class.java)

    @Provides
    @Singleton
    fun provideDb(context: Context): QiitaDb = Room
        .databaseBuilder(context, QiitaDb::class.java, "qiita.db")
        .build()

    @Provides
    @Singleton
    fun provideArticleDao(db: QiitaDb): ArticleDao = db.articleDao()
}