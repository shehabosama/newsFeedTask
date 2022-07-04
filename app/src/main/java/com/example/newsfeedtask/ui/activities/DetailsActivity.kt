package com.example.newsfeedtask.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.newsfeedtask.R
import com.example.newsfeedtask.model.NewsItem
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    lateinit var titleTextView : TextView
    lateinit var createAtTextView : TextView
    lateinit var typeTextView : TextView
    lateinit var webSiteTextView : TextView
    lateinit var newsSectionTextView : TextView
    lateinit var newsImage:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val newsItem = intent.getSerializableExtra("RESULT_KEY") as? NewsItem
        titleTextView = findViewById(R.id.titleTextView)
        createAtTextView = findViewById(R.id.createAtTextView)
        typeTextView = findViewById(R.id.typeTextView)
        webSiteTextView = findViewById(R.id.webSiteTextview)
        newsSectionTextView = findViewById(R.id.newsSectionTextView)
        newsImage = findViewById(R.id.imageView)
        newsItem?.let {
            createAtTextView.text = newsItem.webPublicationDate
            titleTextView.text = newsItem.webTitle
            typeTextView.text = newsItem.type
            webSiteTextView.text = newsItem.webUrl
            newsSectionTextView.text = newsItem.sectionName
            Picasso.get()
                .load(newsItem.fields.thumbnail)
                .placeholder(R.drawable.news_icon)
                .into(newsImage)
        }



    }
}