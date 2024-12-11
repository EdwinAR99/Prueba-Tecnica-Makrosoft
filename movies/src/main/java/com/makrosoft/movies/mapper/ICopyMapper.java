package com.makrosoft.movies.mapper;

import org.mapstruct.*;

import com.makrosoft.movies.dto.request.copy.CopyDtoCreateRequest;
import com.makrosoft.movies.dto.response.copy.CopyDtoCreateResponse;
import com.makrosoft.movies.dto.response.copy.CopyDtoFindResponse;
import com.makrosoft.movies.model.Copy;

/**
 * Class that defines the mapper for converting between Copy entity and Copy DTOs.
 */
@Mapper(componentModel = "spring", uses = IMovieMapper.class)
public interface ICopyMapper {

    @Mappings({
        @Mapping(target = "id", source = "id"),
        @Mapping(target = "code", source = "code"),
        @Mapping(target = "status", source = "status"),
        @Mapping(target = "createUser", source = "createUser"),
        @Mapping(target = "createTime", source = "createTime")
    })
    CopyDtoCreateResponse toDtoCreate(final Copy copy);

    @Mappings({
        @Mapping(target = "id", source = "id"),
        @Mapping(target = "code", source = "code"),
        @Mapping(target = "status", source = "status"),
        @Mapping(target = "createUser", source = "createUser"),
        @Mapping(target = "createTime", source = "createTime"),
        @Mapping(target = "updateUser", source = "updateUser"),
        @Mapping(target = "updateTime", source = "updateTime")
    })
    CopyDtoFindResponse toDtoFind(final Copy copy);

    @Mappings({
        @Mapping(target = "code", source = "code"),
        @Mapping(target = "status", source = "status")
    })
    Copy toEntityCreate(final CopyDtoCreateRequest copyDtoCreateRequest);

}
