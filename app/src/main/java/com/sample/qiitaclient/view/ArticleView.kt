package com.sample.qiitaclient.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.sample.qiitaclient.R
import com.sample.qiitaclient.bindView
import com.sample.qiitaclient.model.Article

class ArticleView : FrameLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context,
                attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context,
                attrs: AttributeSet?,
                defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context,
                attrs: AttributeSet?,
                defStyleAttr: Int,
                defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    private val profileImageView: ImageView
    private val titleTextView: TextView
    private val userNameTextView: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.view_article, this)
        profileImageView = findViewById(R.id.profile_image_view)
        titleTextView = findViewById(R.id.title_text_view)
        userNameTextView = findViewById(R.id.user_name_text_view)
    }

    fun setArticle(article: Article) {
        titleTextView.text = article.title
        userNameTextView.text = article.user.name
        Glide.with(context).load(article.user.profileImageUrl).into(profileImageView)
    }

}
