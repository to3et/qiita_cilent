package com.sample.qiitaclient

import android.os.Bundle
import android.view.Menu
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sample.qiitaclient.databinding.ActivityMainBinding
import com.sample.qiitaclient.model.Article
import com.sample.qiitaclient.viewmodel.ArticleListViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    private lateinit var toolbar: Toolbar

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ArticleListViewModel::class.java)
    }

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as QiitaClientApp).component.inject(this)

        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        setupListView()
    }

    private fun setupListView() {
        listView = findViewById(R.id.list_view)
        val listAdapter = ArticleListAdapter(this)
        listView.adapter = listAdapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = ArticleActivity.intent(
                this,
                listView.adapter.getItem(position) as Article
            )
            startActivity(intent)
        }

        viewModel.articleList.observe(this, Observer {
            listAdapter.articles = it
            listAdapter.notifyDataSetChanged()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchView = menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                query ?: return false
                searchView.clearFocus()
                viewModel.search(query)
                return false
            }
        })
        return true
    }
}