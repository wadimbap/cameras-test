package com.wadimbap.cameras.dto.mapper;

import com.wadimbap.cameras.dto.AggregatedDataDTO;
import com.wadimbap.cameras.model.request.Camera;
import com.wadimbap.cameras.model.request.SourceData;
import com.wadimbap.cameras.model.request.TokenData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CameraMapper {

    CameraMapper INSTANCE = Mappers.getMapper(CameraMapper.class);

    @Mapping(target = "id", source = "camera.id")
    @Mapping(target = "urlType", source = "sourceData.urlType")
    @Mapping(target = "videoUrl", source = "sourceData.videoUrl")
    @Mapping(target = "value", source = "tokenData.value")
    @Mapping(target = "ttl", source = "tokenData.ttl")
    AggregatedDataDTO toAggregatedDataDTO(Camera camera, SourceData sourceData, TokenData tokenData);
}