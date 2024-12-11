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

    /**
     * Converts a Copy entity to a CopyDtoCreateResponse.
     *
     * @param copy The Copy entity to convert.
     * @return The corresponding CopyDtoCreateResponse.
     */
    CopyDtoCreateResponse toDtoCreate(final Copy copy);

    /**
     * Converts a Copy entity to a CopyDtoFindResponse.
     *
     * @param copy The Copy entity to convert.
     * @return The corresponding CopyDtoFindResponse.
     */
    CopyDtoFindResponse toDtoFind(final Copy copy);

    /**
     * Converts a CopyDtoCreateRequest to a Copy entity, ignoring certain fields.
     *
     * @param copyDtoCreateRequest The CopyDtoCreateRequest to convert.
     * @return The corresponding Copy entity.
     */
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "createTime", ignore = true),
        @Mapping(target = "createUser", ignore = true),
        @Mapping(target = "updateTime", ignore = true),
        @Mapping(target = "updateUser", ignore = true),
        @Mapping(target = "movie", ignore = true)
    })
    Copy toEntityCreate(final CopyDtoCreateRequest copyDtoCreateRequest);

}
