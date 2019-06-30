package com.sample.qiitaclient.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.qiitaclient.repository.ArticleRefreshError
import com.sample.qiitaclient.repository.ArticleRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticleListViewModel @Inject constructor(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    val articleList = articleRepository.article

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.value = false
    }

    fun search(query: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                articleRepository.search(query)
            } catch (e: ArticleRefreshError) {
                // メッセージ表示
            } finally {
                _isLoading.value = false
            }
        }
    }
}