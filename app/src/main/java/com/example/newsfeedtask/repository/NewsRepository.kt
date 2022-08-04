package com.example.newsfeedtask.repository
import android.util.Log

import com.example.newsfeedtask.db.mapper.NewsItemCacheMapper
import com.example.newsfeedtask.db.daos.NewsItemDao
import com.example.newsfeedtask.model.NewsItem
import com.example.newsfeedtask.model.NewsResponse
import com.example.newsfeedtask.network.mapper.NewsItemNetworkMapper
import com.example.newsfeedtask.network.RetrofitAPI
import com.example.newsfeedtask.network.entities.MainResponse
import com.example.newsfeedtask.network.mapper.NewsResponseMapper
import com.example.newsfeedtask.util.DataState
import com.example.newsfeedtask.util.safeApiCall
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository
@Inject
constructor(
    private val retrofit: RetrofitAPI,
    private val newsItemDao: NewsItemDao,
    private val newsItemCacheMapper: NewsItemCacheMapper,
    private val newsItemNetworkMapper: NewsItemNetworkMapper,
    private val newsResponseMapper: NewsResponseMapper
)
{
    suspend fun getNewsItem(pageNumber:Int): Flow<DataState<List<NewsItem>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try{
            val networkBlogs = retrofit.getNewsData(page = pageNumber ,api_key = "test")
            val results = newsItemNetworkMapper.mapFromEntityList(networkBlogs.response.newsItemNetworkEntities)
            for(newsItem in results){
                newsItemDao.insertBlog(newsItemCacheMapper.mapToEntity(newsItem))
            }
//            val cachedBlogs = newsItemDao.getNewsItemEntities()
            emit(DataState.Success(results))
        }catch (e: Exception){
            Log.e("error", "getBlogs: ",e.fillInStackTrace() )
            emit(DataState.Error(e))
        }
    }

    suspend fun getNewsResponse(pageNumber:Int):Flow<DataState<NewsResponse>> = flow {
        emit(DataState.Loading)
        delay(1000)
        emit(safeApiCall {
                val networkResult = retrofit.getNewsData(pageNumber,"test","thumbnail")
                val result = newsResponseMapper.mapFromEntity(networkResult.response)
                result
            }
        )

    }

//    suspend fun getNewsResponse(pageNumber:Int): Flow<DataState<NewsResponse>> = flow {
//        emit(DataState.Loading)
//        delay(1000)
//        try{
//            val networkBlogs = retrofit.getNewsData(page = pageNumber ,api_key = "test")
//
//            val results = newsResponseMapper.mapFromEntity(networkBlogs.response)
//
//            emit(DataState.Success(results))
//        }catch (e: Exception){
//            Log.e("error", "getBlogs: ",e.fillInStackTrace() )
//            emit(DataState.Error(e))
//        }
//    }
    suspend fun getOffLineNewsItem(): Flow<DataState<List<NewsItem>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try{
            val cachedBlogs = newsItemDao.getNewsItemEntities()
            emit(DataState.Success(newsItemCacheMapper.mapFromEntityList(cachedBlogs)))
        }catch (e: Exception){
            Log.e("error", "getBlogs: ",e.fillInStackTrace() )
            emit(DataState.Error(e))
        }
    }
}