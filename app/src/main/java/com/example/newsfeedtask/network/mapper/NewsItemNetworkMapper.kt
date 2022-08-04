package com.example.newsfeedtask.network.mapper
import android.util.Log
import com.example.newsfeedtask.model.Fields
import com.example.newsfeedtask.model.NewsItem
import com.example.newsfeedtask.network.entities.FieldsNetwrokEntity
import com.example.newsfeedtask.network.entities.NewsItemNetworkEntity
import com.example.newsfeedtask.util.EntityMapper
import javax.inject.Inject
class NewsItemNetworkMapper @Inject
constructor() : EntityMapper<NewsItemNetworkEntity, NewsItem> {
    @Inject
    lateinit var filedMapper:FieldMapper
    override fun mapFromEntity(entity: NewsItemNetworkEntity): NewsItem {
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
            fields = filedMapper.mapFromEntity(entity.fieldsNetworkEntity?: FieldsNetwrokEntity(""))
                 )
    }

    override fun mapToEntity(domainModel: NewsItem): NewsItemNetworkEntity {
        return NewsItemNetworkEntity(
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
            fieldsNetworkEntity =filedMapper.mapToEntity(domainModel.fields?: Fields(""))
        )
    }

    fun mapFromEntityList(entities:List<NewsItemNetworkEntity>):List<NewsItem>{
        return entities.map { mapFromEntity(it) }
    }
    fun mapToEntityList(domainsEntities :List<NewsItem>):List<NewsItemNetworkEntity>{
        return domainsEntities.map { mapToEntity(it) }
    }
}