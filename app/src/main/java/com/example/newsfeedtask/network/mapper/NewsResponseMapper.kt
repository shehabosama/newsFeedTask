package com.example.newsfeedtask.network.mapper

import com.example.newsfeedtask.model.NewsResponse
import com.example.newsfeedtask.network.entities.NewsResponseNetworkEntity
import com.example.newsfeedtask.util.EntityMapper
import javax.inject.Inject

class NewsResponseMapper @Inject constructor():EntityMapper<NewsResponseNetworkEntity,NewsResponse> {
    @Inject
    lateinit var newsNetworkItemMapper: NewsItemNetworkMapper
    override fun mapFromEntity(entity: NewsResponseNetworkEntity): NewsResponse {
        return NewsResponse(
            currentPage = entity.currentPage,
            orderBy = entity.orderBy,
            pageSize =  entity.pageSize ,
            pages = entity.pages ,
            newsItems = newsNetworkItemMapper.mapFromEntityList( entity.newsItemNetworkEntities),
            startIndex = entity.startIndex ,
            status = entity.status ,
            total = entity.total ,
            userTier = entity.userTier
        )
    }

    override fun mapToEntity(domainModel: NewsResponse): NewsResponseNetworkEntity {
        return NewsResponseNetworkEntity(
            currentPage = domainModel.currentPage,
            orderBy = domainModel.orderBy,
            pageSize =  domainModel.pageSize ,
            pages = domainModel.pages ,
            newsItemNetworkEntities = newsNetworkItemMapper.mapToEntityList( domainModel.newsItems),
            startIndex = domainModel.startIndex ,
            status = domainModel.status ,
            total = domainModel.total ,
            userTier = domainModel.userTier
        )
    }
}