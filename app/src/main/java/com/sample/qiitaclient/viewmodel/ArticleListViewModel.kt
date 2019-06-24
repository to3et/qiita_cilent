package com.sample.qiitaclient.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.qiitaclient.client.ArticleClient
import com.sample.qiitaclient.model.Article
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ArticleListViewModel @Inject constructor(
    private val articleClient: ArticleClient
) : ViewModel(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }

    private val _articleList = MutableLiveData<List<Article>>()
    val articleList: LiveData<List<Article>>
        get() = _articleList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.value = false
    }

    fun search(query: String) {
        _isLoading.value = true
        launch {
             _articleList.value = withContext(Dispatchers.Default) {
                articleClient.search(query).await()
             }
        }
        _isLoading.value = false
    }
}