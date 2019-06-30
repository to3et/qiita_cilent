package com.sample.qiitaclient.entity

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Article(
    @PrimaryKey val id: String,
    val title: String,
    val url: String,
    val commentsCount: Int,
    val likesCount: Int,
    val reactionsCount: Int,
    val pageViewsCount: Int,
    val createdAt: String,
    @Embedded(prefix = "user_") val user: User): Parcelable
