package com.example.newsfeedtask.repository
import android.util.Log
import com.example.newsfeedtask.db.mapper.FavNewsItemCacheMapper
import com.example.newsfeedtask.db.daos.FavoriteDao

import com.example.newsfeedtask.model.NewsItem
import com.example.newsfeedtask.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavNewsRepository
@Inject
constructor(
    private val newsItemDao: FavoriteDao,
    private val newsItemCacheMapper: FavNewsItemCacheMapper,
   )
{
    suspend fun insertFavNewsItem(newsItem:NewsItem): Flow<DataState<String>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try{
            val count = newsItemDao.insertNewsFeedItem(newsItemCacheMapper.mapToEntity(newsItem))
            if(count>0)
                emit(DataState.Success("Added to favorite item Successfully!"))
            else
               throw Exception("test")

        }catch (e: Exception){
            Log.e("error", "getBlogs: ",e.fillInStackTrace() )
            emit(DataState.Error(e))
        }
    }
    suspend fun deleteFavNewsItem(newsItem:NewsItem ): Flow<DataState<String>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try{
            newsItemDao.deleteFavNews(newsItem.id)
            emit(DataState.Success("Item Delete Successfully!" ))
        }catch (e: Exception){
            Log.e("error", "getBlogs: ",e.fillInStackTrace() )
            emit(DataState.Error(e))
        }
    }
    suspend fun getFavNewsItems(): Flow<DataState<List<NewsItem>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try{

            val cachedBlogs = newsItemDao.getFavNewItemEntities()
            emit(DataState.Success(newsItemCacheMapper.mapFromEntityList(cachedBlogs)))
        }catch (e: Exception){
            Log.e("error", "getBlogs: ",e.fillInStackTrace() )
            emit(DataState.Error(e))
        }
    }
}