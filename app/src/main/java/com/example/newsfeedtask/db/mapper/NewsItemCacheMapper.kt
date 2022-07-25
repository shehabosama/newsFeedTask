package com.example.newsfeedtask.db.mapper
import com.example.newsfeedtask.db.entities.FieldsCacheEntity
import com.example.newsfeedtask.db.entities.NewsItemCacheEntity
import com.example.newsfeedtask.model.Fields
import com.example.newsfeedtask.model.NewsItem
import com.example.newsfeedtask.util.EntityMapper
import javax.inject.Inject
class NewsItemCacheMapper @Inject
constructor() : EntityMapper<NewsItemCacheEntity, NewsItem> {
    @Inject
    lateinit var fieldMapper:FieldCacheMapper
    override fun mapFromEntity(entity: NewsItemCacheEntity): NewsItem {
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
            webUrl = entity.webUrl,
            fields =  fieldMapper.mapFromEntity(entity.fieldsCacheEntity?: FieldsCacheEntity(""))
        )

    }

    override fun mapToEntity(domainModel: NewsItem): NewsItemCacheEntity {
        return NewsItemCacheEntity(
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
            webUrl = domainModel.webUrl,
            fieldsCacheEntity =fieldMapper.mapToEntity(domainModel.fields?:Fields(""))
        )
    }

    fun mapFromEntityList(entities:List<NewsItemCacheEntity>):List<NewsItem>{

        return entities.map { mapFromEntity(it) }
    }


}