package com.example.newsfeedtask.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.newsfeedtask.R
import com.example.newsfeedtask.model.NewsItem

class DetailsActivity : AppCompatActivity() {
    lateinit var titleTextView : TextView
    lateinit var createAtTextView : TextView
    lateinit var typeTextView : TextView
    lateinit var webSiteTextView : TextView
    lateinit var newsSectionTextView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val result = intent.getSerializableExtra("RESULT_KEY") as? NewsItem
        titleTextView = findViewById(R.id.titleTextView)
        createAtTextView = findViewById(R.id.createAtTextView)
        typeTextView = findViewById(R.id.typeTextView)
        webSiteTextView = findViewById(R.id.webSiteTextview)
        newsSectionTextView = findViewById(R.id.newsSectionTextView)
        result?.let {
            createAtTextView.text = result.webPublicationDate
            titleTextView.text = result.webTitle
            typeTextView.text = result.type
            webSiteTextView.text = result.webUrl
            newsSectionTextView.text = result.sectionName
        }



    }
}