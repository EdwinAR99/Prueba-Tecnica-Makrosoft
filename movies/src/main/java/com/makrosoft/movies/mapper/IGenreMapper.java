package com.makrosoft.movies.mapper;

import org.mapstruct.*;

import com.makrosoft.movies.dto.request.genre.GenreDtoCreateRequest;
import com.makrosoft.movies.dto.response.genre.*;
import com.makrosoft.movies.model.Genre;

/**
 * Class that defines the mapper in charge of the O/R transition to a DTO object.
 */
@Mapper(componentModel = "spring")
public interface IGenreMapper {

    /**
     * Converts a Genre entity to a GenreDtoCreateResponse.
     *
     * @param genre The Genre entity to convert.
     * @return The corresponding GenreDtoCreateResponse.
     */
    GenreDtoCreateResponse toDtoCreate(final Genre genre);

    /**
     * Converts a Genre entity to a GenreDtoFindResponse.
     *
     * @param genre The Genre entity to convert.
     * @return The corresponding GenreDtoFindResponse.
     */
    GenreDtoFindResponse toDtoFind(final Genre genre);

    /**
     * Converts a GenreDtoCreateRequest to a Genre entity, ignoring certain fields
     * such as id, createTime, updateTime, and movies.
     *
     * @param genreDtoCreateRequest The GenreDtoCreateRequest to convert.
     * @return The corresponding Genre entity.
     */
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "createTime", ignore = true),
        @Mapping(target = "createUser", ignore = true),
        @Mapping(target = "updateTime", ignore = true),
        @Mapping(target = "updateUser", ignore = true),
        @Mapping(target = "movies", ignore = true)
    })
    Genre toEntityCreate(final GenreDtoCreateRequest genreDtoCreateRequest);

}
