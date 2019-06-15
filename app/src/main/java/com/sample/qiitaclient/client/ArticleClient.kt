package com.sample.qiitaclient.client

import com.sample.qiitaclient.model.Article
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface ArticleClient {

    @GET("/api/v2/items")
    fun search(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        @Query("query") query: String
    ): Observable<List<Article>>
}