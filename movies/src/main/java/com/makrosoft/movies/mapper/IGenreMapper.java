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

    @Mappings({
        @Mapping(target = "id",source = "id"),
        @Mapping(target = "name", source = "name"),
        @Mapping(target = "rentalPrice", source = "rentalPrice"),
        @Mapping(target = "createTime", source = "createTime"),
        @Mapping(target = "createUser", source = "createUser")
    })
    GenreDtoCreateResponse toDtoCreate(final Genre genre);

    @Mappings({
        @Mapping(target = "id", source = "id"),
        @Mapping(target = "name", source = "name"),
        @Mapping(target = "rentalPrice", source = "rentalPrice"),
        @Mapping(target = "createTime", source = "createTime"),
        @Mapping(target = "createUser", source = "createUser"),
        @Mapping(target = "updateTime", source = "updateTime"),
        @Mapping(target = "updateUser", source = "updateUser")
    })
    GenreDtoFindResponse toDtoFind(final Genre genre);

    @Mappings({
        @Mapping(target = "name", source = "name"),
        @Mapping(target = "rentalPrice", source = "rentalPrice")
    })
    Genre toEntityCreate(final GenreDtoCreateRequest genreDtoCreateRequest);



}
