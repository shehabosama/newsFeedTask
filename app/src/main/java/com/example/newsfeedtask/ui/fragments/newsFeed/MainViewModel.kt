package com.example.newsfeedtask.ui.fragments.newsFeed

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.newsfeedtask.model.NewsItem
import com.example.newsfeedtask.model.NewsResponse
import com.example.newsfeedtask.repository.FavNewsRepository
import com.example.newsfeedtask.repository.NewsRepository
import com.example.newsfeedtask.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val favNewsRepository: FavNewsRepository
    ) :ViewModel(){

    private val _dataNewsState:MutableLiveData<DataState<List<NewsItem>>> = MutableLiveData()
    val dataNewsState:LiveData<DataState<List<NewsItem>>>
    get() = _dataNewsState

    private val _dataFavInsertState:MutableLiveData<DataState<String>> = MutableLiveData()
    val dataFavInsertState:LiveData<DataState<String>>
        get() = _dataFavInsertState
    private var mRecyclerViewState: Parcelable? = null

    private val _mainNewsResponse = MutableLiveData<DataState<NewsResponse>>()
    private val mainNewsResponse :LiveData<DataState<NewsResponse>>
    get()=_mainNewsResponse

    fun setStateEvent(mainStateEvent: MainStateEvent, pageNumber:Int=0, newsItem: NewsItem? = null){

        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetNewsEvent -> {
                    newsRepository.getNewsItem(pageNumber).onEach { dataState ->
                        _dataNewsState.value = dataState
                    }.launchIn(viewModelScope)
                }
                is MainStateEvent.AddFavNewsEvent->{
                   favNewsRepository.insertFavNewsItem(newsItem!!).onEach { dataState ->
                       _dataFavInsertState.value =dataState
                   }.launchIn(viewModelScope)
                }
                is MainStateEvent.GetOfflineNewsEvent -> {
                    newsRepository.getOffLineNewsItem().onEach { dataState ->
                        _dataNewsState.value = dataState
                    }.launchIn(viewModelScope)
                }
                is MainStateEvent.GetNewsResponseEvent->{
                    newsRepository.getNewsResponse(pageNumber).onEach {dataState->
                        _mainNewsResponse.value = dataState
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

}
sealed class MainStateEvent{
    object GetNewsEvent: MainStateEvent()
    object GetOfflineNewsEvent:MainStateEvent()
    object AddFavNewsEvent: MainStateEvent()
    object GetNewsResponseEvent:MainStateEvent()

}