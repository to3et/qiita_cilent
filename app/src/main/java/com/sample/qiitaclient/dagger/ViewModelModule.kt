package com.sample.qiitaclient.dagger

import androidx.lifecycle.ViewModel
import com.sample.qiitaclient.viewmodel.ArticleListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ArticleListViewModel::class)
    abstract fun bindArticleListViewModel(viewModel: ArticleListViewModel): ViewModel
}