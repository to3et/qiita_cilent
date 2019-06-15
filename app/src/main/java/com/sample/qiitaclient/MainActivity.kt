package com.sample.qiitaclient

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ListView
import android.widget.ProgressBar
import com.sample.qiitaclient.client.ArticleClient
import com.sample.qiitaclient.model.Article
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import com.trello.rxlifecycle.kotlin.bindToLifecycle
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : RxAppCompatActivity() {

    @Inject
    lateinit var articleClient: ArticleClient

    lateinit var progressBar: ProgressBar

    lateinit var listView: ListView

    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as QiitaClientApp).component.inject(this)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.main_toolbar)
        listView = findViewById(R.id.list_view)
        progressBar = findViewById(R.id.progress_bar)

        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = ArticleActivity.intent(
                this,
                listView.adapter.getItem(position) as Article)
            startActivity(intent)
        }

        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchView = menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?: return false
                hideKeyboard()
                progressBar.visibility = View.VISIBLE
                val listAdapter = ArticleListAdapter(this@MainActivity)
                listView.adapter = listAdapter

                articleClient.search(
                    1,
                    30,
                    "title:${query} tag:${query} body:${query}")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate {
                        progressBar.visibility = View.GONE
                    }
                    .bindToLifecycle(this@MainActivity)
                    .subscribe({
                        listAdapter.articles = it
                        listAdapter.notifyDataSetChanged()
                    }, {
                        toast("エラー: $it")
                    })

                return false
            }
        })
        return true
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}