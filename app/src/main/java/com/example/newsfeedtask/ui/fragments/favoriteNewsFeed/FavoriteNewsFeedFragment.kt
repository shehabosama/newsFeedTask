package com.example.newsfeedtask.ui.fragments.favoriteNewsFeed

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfeedtask.R
import com.example.newsfeedtask.adapters.PaginationAdapter
import com.example.newsfeedtask.model.NewsItem
import com.example.newsfeedtask.ui.activities.DetailsActivity
import com.example.newsfeedtask.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteNewsFeedFragment : Fragment(R.layout.fragment_favorite_news_feed)  , PaginationAdapter.NewsItemInterAction{
    private val viewModel: FavFragmentViewModel by viewModels()
    private lateinit var progressBar: ProgressBar
    lateinit var sb:StringBuilder
    lateinit var paginationAdapter: PaginationAdapter
    lateinit var recyclerView: RecyclerView
    private var listOfNewsItems: MutableList<NewsItem>? = mutableListOf()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.progressBar)
        recyclerView = view.findViewById(R.id.recyclerView)
        sb = StringBuilder()
        subscribeObserver()

        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        paginationAdapter =  PaginationAdapter(context,this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = paginationAdapter
        setUpSwipe()
        viewModel.setStateEvent(MainStateEvent.GetFavEvent)
    }

    private fun subscribeObserver() {

        viewModel.dataFavNewsState.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<NewsItem>> -> {
                    displayProgressBar(false)
                    listOfNewsItems!!.addAll(dataState.data)
                    paginationAdapter.addAll(listOfNewsItems!!)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })

        viewModel.deleteDataFavNewsState.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success<String> -> {
                    Toast.makeText(context , dataState.data , Toast.LENGTH_LONG).show()
                    displayProgressBar(false)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun setUpSwipe() {
        val callback: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val swipedPokemonPosition = viewHolder.adapterPosition
                    val result = paginationAdapter.getResultAt(swipedPokemonPosition)
                    viewModel.setStateEvent(MainStateEvent.DeleteFavEvent , newsItem = result )
                    paginationAdapter.removeAt(swipedPokemonPosition)


                }
                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {
                    if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                        var icon = ContextCompat.getDrawable(context!!, R.drawable.ic_star_gold_24dp)
                        var iconLeft = 0
                        var iconRight = 0
                        var background: ColorDrawable = ColorDrawable(Color.YELLOW)
                        val itemView = viewHolder.itemView
                        val margin = convertDpToPx(32)
                        val iconWidth = icon!!.intrinsicWidth
                        val iconHeight = icon.intrinsicHeight
                        val cellHeight = itemView.bottom - itemView.top
                        val iconTop = itemView.top + (cellHeight - iconHeight) / 2
                        val iconBottom = iconTop + iconHeight
                        // Right swipe.
                        if (dX > 0) {
                            icon = ContextCompat.getDrawable(context!!, R.drawable.ic_baseline_delete_forever_24)
                            background = ColorDrawable(Color.RED)
                            background.setBounds(
                                0,
                                itemView.getTop(),
                                (itemView.getLeft() + dX).toInt(),
                                itemView.getBottom()
                            )
                            iconLeft = margin
                            iconRight = margin + iconWidth
                        }
                        background.draw(c)
                        icon?.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                        icon?.draw(c)
                    }
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                }
            }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun convertDpToPx(dp: Int): Int {
        return Math.round(dp * (resources.displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }
    private fun displayError(message:String?){
        if(message !=null){
            Log.e("displayError: ",message )
        }else{
            Log.e("displayError: ","Unknown error" )
        }
    }
    private fun displayProgressBar(isDisplayed:Boolean){
        progressBar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

    override fun onClick(newsItem: NewsItem?, position: Int) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra("RESULT_KEY", newsItem)
        startActivity(intent)
    }
}