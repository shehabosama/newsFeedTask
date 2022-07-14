package com.example.newsfeedtask.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newsfeedtask.R
import com.example.newsfeedtask.ui.fragments.favoriteNewsFeed.FavoriteNewsFeedFragment
import com.example.newsfeedtask.ui.fragments.newsFeed.NewsFeedFragment
import com.example.newsfeedtask.ui.fragments.uploadImage.UploadImageToServerFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import dagger.hilt.android.AndroidEntryPoint


lateinit var chipNavigationBar: ChipNavigationBar;
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chipNavigationBar = findViewById(R.id.bottom_nav_bar)
        chipNavigationBar.setItemSelected(
            R.id.home,
            true
        )
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
               UploadImageToServerFragment()
            ).commit()
        bottomMenu()
    }

    private fun bottomMenu() {

        chipNavigationBar.setOnItemSelectedListener(object : ChipNavigationBar.OnItemSelectedListener {
           override fun onItemSelected(i: Int) {
                var fragment: Fragment? = null
                when (i) {
                    R.id.home -> fragment = NewsFeedFragment()
                    R.id.activity -> fragment = FavoriteNewsFeedFragment()

                }
                supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.fragment_container,
                        fragment!!
                    ).commit()
            }
        })
    }

}