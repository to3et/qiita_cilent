package com.sample.qiitaclient.client

import com.sample.qiitaclient.model.Article
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleClient {

    @GET("/api/v2/items")
    fun search(
        @Query("query") query: String
    ): Deferred<List<Article>>
}