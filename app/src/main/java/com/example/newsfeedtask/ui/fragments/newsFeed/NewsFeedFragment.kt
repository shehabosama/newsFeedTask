package com.example.newsfeedtask.ui.fragments.newsFeed

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfeedtask.R
import com.example.newsfeedtask.adapters.PaginationAdapter
import com.example.newsfeedtask.databinding.FragmentNewsFeedBinding
import com.example.newsfeedtask.model.NewsItem
import com.example.newsfeedtask.ui.activities.DetailsActivity
import com.example.newsfeedtask.util.DataState
import com.example.newsfeedtask.util.Helper
import com.example.newsfeedtask.util.PaginationScrollListener
import com.example.newsfeedtask.util.viewBinding
import com.example.newsfeedtask.widgets.toast.ErrorToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
@AndroidEntryPoint
open class NewsFeedFragment : Fragment(R.layout.fragment_news_feed) , PaginationAdapter.NewsItemInterAction {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var sb:StringBuilder
    private lateinit var paginationAdapter: PaginationAdapter
    private var currentPage = 1
    private var isLoading:Boolean = false
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var grideLayoutMangager: GridLayoutManager
    private val binding by viewBinding(FragmentNewsFeedBinding::bind)
    private val newsList:MutableList<NewsItem> = mutableListOf()
    @Inject
    lateinit var helper:Helper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()

        binding.recyclerView.addOnScrollListener(object :PaginationScrollListener(linearLayoutManager){
            override fun loadMoreItems() {
                isLoading = true
                currentPage += 1
                loadNextPage(currentPage)
            }
            override fun isLastPage(): Boolean {
              return false
            }
            override fun isLoading(): Boolean {
                return false
            }
        })

        if (helper.checkForInternet(requireContext())) {
            viewModel.setStateEvent(MainStateEvent.GetNewsEvent, 1)
        } else {
            Toast.makeText(context , "You are offline now" , Toast.LENGTH_LONG).show()
            viewModel.setStateEvent(MainStateEvent.GetOfflineNewsEvent)
        }
    }
    private fun loadNextPage(pageNumber:Int) {
        viewModel.setStateEvent(MainStateEvent.GetNewsEvent, pageNumber)
    }
    private fun initialization()=with(binding){
        sb = StringBuilder()
        recyclerViewSetup()
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = paginationAdapter
        setUpSwipe()
        subscribeObserver()
    }
    private fun recyclerViewSetup(){
        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        grideLayoutMangager = GridLayoutManager(context,2)
        paginationAdapter =  PaginationAdapter(context,this)
    }
    private fun subscribeObserver() {

        viewModel.dataNewsState.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<NewsItem>> -> {
                    displayProgressBar(false)
                    newsList.addAll(dataState.data)
                    paginationAdapter.addAll(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                   ErrorToast.makeShortText(requireActivity() , dataState.exception.message!!).show()
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })

        viewModel.dataFavInsertState.observe(viewLifecycleOwner, Observer { dataState->
            when(dataState){
                is DataState.Success<String>->{
                    Toast.makeText(context , dataState.data , Toast.LENGTH_LONG).show()
                }
                is DataState.Error->{
                    displayError(dataState.exception.message)

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
                    viewModel.setStateEvent(MainStateEvent.AddFavNewsEvent , newsItem = result)

                    paginationAdapter.notifyDataSetChanged()

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
                            icon = ContextCompat.getDrawable(context!!, R.drawable.ic_star_gold_24dp)
                            background = ColorDrawable(Color.WHITE)
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
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

    private fun convertDpToPx(dp: Int): Int {
        return Math.round(dp * (resources.displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if(requireActivity().resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.recyclerView.layoutManager = grideLayoutMangager
            binding.recyclerView.adapter = paginationAdapter
            paginationAdapter.addAll(newsList)
        }else{
            binding.recyclerView.layoutManager = linearLayoutManager
            binding.recyclerView.adapter = paginationAdapter
            paginationAdapter.addAll(newsList)
        }
    }

    private fun displayError(message:String?){
        if(message !=null){
            Log.e("displayError: ",message )
        }else{
            Log.e("displayError: ","Unknown error" )
        }
    }
    private fun displayProgressBar(isDisplayed:Boolean){
        binding.progressBar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

    override fun onClick(newsItem: NewsItem?, position: Int) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra("RESULT_KEY", newsItem)
        startActivity(intent)
     //   SuccessDialog.Builder(requireContext()).setMessage("hjghf").setCancelable(true).show()
    }

}


fun <T> ComponentActivity.collectLatestLifecycleFlow(flow: Flow<T>, collect: suspend (T)->Unit){
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED){
            flow.collectLatest(collect)
        }
    }
}


