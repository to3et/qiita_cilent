package com.sample.qiitaclient.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.sample.qiitaclient.client.ArticleClient
import com.sample.qiitaclient.db.ArticleDao
import com.sample.qiitaclient.entity.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(
    private val articleClient: ArticleClient,
    private val articleDao: ArticleDao
) {
    val article: LiveData<List<Article>> by lazy {
        articleDao.load()
    }

    suspend fun search(query: String) {
        withContext(Dispatchers.IO) {
            val response = articleClient.search(query)
            if (response.isSuccessful) {
                articleDao.deleteAll()
                articleDao.insertAll(response.body()!!)
            } else {
                throw ArticleRefreshError(response.message())
            }
        }
    }
}

class ArticleRefreshError(message: String) : Throwable(message)