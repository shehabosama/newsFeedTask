package com.example.newsfeedtask.ui.fragments.favoriteNewsFeed

import androidx.lifecycle.*

import com.example.newsfeedtask.model.NewsItem
import com.example.newsfeedtask.repository.FavNewsRepository
import com.example.newsfeedtask.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
@ExperimentalCoroutinesApi
@HiltViewModel
class FavFragmentViewModel @Inject constructor(
    private val favNewsRepository: FavNewsRepository,
    ) :ViewModel(){

    private val _dataFavNewsState:MutableLiveData<DataState<List<NewsItem>>> = MutableLiveData()
    val dataFavNewsState:LiveData<DataState<List<NewsItem>>>
    get() = _dataFavNewsState

    private val _deleteDataFavNewsState:MutableLiveData<DataState<String>> = MutableLiveData()
    val deleteDataFavNewsState:LiveData<DataState<String>>
    get() = _deleteDataFavNewsState

    fun setStateEvent(mainStateEvent: MainStateEvent, newsItem: NewsItem? = null ){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetFavEvent -> {
                    favNewsRepository.getFavNewsItems().onEach { dataState ->
                        _dataFavNewsState.value = dataState
                    }.launchIn(viewModelScope)
                }
                is MainStateEvent.DeleteFavEvent->{
                    favNewsRepository.deleteFavNewsItem(newsItem!!).onEach { dataState->
                        _deleteDataFavNewsState.value = dataState
                    }.launchIn(viewModelScope)
                }
                is MainStateEvent.None ->{
                    //who cares
                }

            }
        }
    }

}
sealed class MainStateEvent{
    object GetFavEvent: MainStateEvent() // state to get data from data source
    object DeleteFavEvent:MainStateEvent()
    object None: MainStateEvent() // optional if you want do anything else
}