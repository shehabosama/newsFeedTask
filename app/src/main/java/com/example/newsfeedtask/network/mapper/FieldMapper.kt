package com.example.newsfeedtask.network.mapper

import com.example.newsfeedtask.model.Fields
import com.example.newsfeedtask.network.entities.FieldsNetwrokEntity
import com.example.newsfeedtask.util.EntityMapper
import javax.inject.Inject

class FieldMapper @Inject constructor() : EntityMapper<FieldsNetwrokEntity, Fields> {
    override fun mapFromEntity(entity: FieldsNetwrokEntity): Fields {
       return Fields(thumbnail = entity.thumbnail)
    }

    override fun mapToEntity(domainModel: Fields): FieldsNetwrokEntity {
        return FieldsNetwrokEntity(thumbnail = domainModel.thumbnail)
    }
}