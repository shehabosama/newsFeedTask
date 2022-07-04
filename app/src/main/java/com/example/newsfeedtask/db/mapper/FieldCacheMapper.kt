package com.example.newsfeedtask.db.mapper

import com.example.newsfeedtask.db.entities.FieldsCacheEntity
import com.example.newsfeedtask.model.Fields
import com.example.newsfeedtask.network.entities.FieldsNetwrokEntity
import com.example.newsfeedtask.util.EntityMapper
import javax.inject.Inject

class FieldCacheMapper @Inject constructor() : EntityMapper<FieldsCacheEntity, Fields> {
    override fun mapFromEntity(entity: FieldsCacheEntity): Fields {
       return Fields(thumbnail = entity.thumbnail)
    }

    override fun mapToEntity(domainModel: Fields): FieldsCacheEntity {
        return FieldsCacheEntity(thumbnail = domainModel.thumbnail)
    }
}