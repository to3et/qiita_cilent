package com.sample.qiitaclient.client

import com.sample.qiitaclient.entity.Article
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleClient {

    @GET("/api/v2/items")
    suspend fun search(
        @Query("query") query: String
    ): Response<List<Article>>
}