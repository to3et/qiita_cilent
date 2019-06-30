package com.sample.qiitaclient.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.qiitaclient.entity.Article

@Database(
    entities = [Article::class],
    version = 1
)
abstract class QiitaDb : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}