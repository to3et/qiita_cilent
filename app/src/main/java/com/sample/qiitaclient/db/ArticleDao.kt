package com.sample.qiitaclient.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.sample.qiitaclient.entity.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = REPLACE)
    fun insertAll(articles: List<Article>)

    @Query("SELECT * FROM article ORDER BY createdAt DESC")
    fun load(): LiveData<List<Article>>

    @Query("DELETE FROM article")
    fun deleteAll()
}