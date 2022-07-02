package com.example.newsfeedtask.db.mapper
import com.example.newsfeedtask.db.entities.FavoriteNewsCacheEntity
import com.example.newsfeedtask.model.NewsItem
import com.example.newsfeedtask.util.EntityMapper
import javax.inject.Inject
class FavNewsItemCacheMapper @Inject
constructor() : EntityMapper<FavoriteNewsCacheEntity, NewsItem> {
    override fun mapFromEntity(entity: FavoriteNewsCacheEntity): NewsItem {
        return NewsItem(
            apiUrl = entity.apiUrl,
            id = entity.id ,
            isHosted = entity.isHosted,
            pillarId = entity.pillarId,
            pillarName = entity.pillarName,
            sectionId = entity.sectionId,
            sectionName = entity.sectionName,
            type = entity.type,
            webPublicationDate = entity.webPublicationDate,
            webTitle = entity.webTitle,
            webUrl = entity.webUrl)
    }

    override fun mapToEntity(domainModel: NewsItem): FavoriteNewsCacheEntity {
        return FavoriteNewsCacheEntity(
            apiUrl = domainModel.apiUrl,
            id = domainModel.id ,
            isHosted = domainModel.isHosted,
            pillarId = domainModel.pillarId,
            pillarName = domainModel.pillarName,
            sectionId = domainModel.sectionId,
            sectionName = domainModel.sectionName,
            type = domainModel.type,
            webPublicationDate = domainModel.webPublicationDate,
            webTitle = domainModel.webTitle,
            webUrl = domainModel.webUrl)
    }

    fun mapFromEntityList(entities:List<FavoriteNewsCacheEntity>):List<NewsItem>{

        return entities.map { mapFromEntity(it) }
    }


}