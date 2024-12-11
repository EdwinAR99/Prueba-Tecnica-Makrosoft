package com.makrosoft.movies.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import com.makrosoft.movies.dto.request.movie.MovieDtoCreateRequest;
import com.makrosoft.movies.dto.response.movie.MovieDtoAvailableResponse;
import com.makrosoft.movies.dto.response.movie.MovieDtoCreateResponse;
import com.makrosoft.movies.dto.response.movie.MovieDtoFindResponse;
import com.makrosoft.movies.dto.response.movie.MovieDtoReportResponse;
import com.makrosoft.movies.model.CopyStatusEnum;
import com.makrosoft.movies.model.Movie;

/**
 * Class that defines the mapper for converting between Movie entity and Movie
 * DTOs.
 */
@Mapper(componentModel = "spring", uses = IGenreMapper.class)
public interface IMovieMapper {

    IMovieMapper INSTANCE = Mappers.getMapper(IMovieMapper.class);

    @Mappings({
        @Mapping(target = "id", source = "id"),
        @Mapping(target = "name", source = "name"),
        @Mapping(target = "description", source = "description"),
        @Mapping(target = "genre", source = "genre"),
        @Mapping(target = "createUser", source = "createUser"),
        @Mapping(target = "createTime", source = "createTime")
    })
    MovieDtoCreateResponse toDtoCreate(final Movie movie);

    @Mappings({
        @Mapping(target = "id", source = "id"),
        @Mapping(target = "name", source = "name"),
        @Mapping(target = "description", source = "description"),
        @Mapping(target = "genre", source = "genre"),
        @Mapping(target = "createUser", source = "createUser"),
        @Mapping(target = "createTime", source = "createTime"),
        @Mapping(target = "updateUser", source = "updateUser"),
        @Mapping(target = "updateTime", source = "updateTime")
    })
    MovieDtoFindResponse toDtoFind(final Movie movie);

    @Mappings({
        @Mapping(target = "name", source = "name"),
        @Mapping(target = "description", source = "description"),
    })
    Movie toEntityCreate(final MovieDtoCreateRequest movieDtoCreateRequest);

    @Mappings(
        @Mapping(target = "availableCopies", expression = "java(getAvailableCopies(movie))")
    )
    MovieDtoAvailableResponse toDtoAvailable(Movie movie);

    default int getAvailableCopies(Movie movie) {
        return (int) movie.getCopies().stream()
                .filter(copy -> copy.getStatus().equals(CopyStatusEnum.DISPONIBLE))
                .count();
    }

    @Mappings({
        @Mapping(target = "name", source = "name"),
        @Mapping(target = "description", source = "description"),
        @Mapping(target = "timesRented", source = "timesRented"),
        @Mapping(target = "totalRevenue", source = "totalRevenue")
    })
    MovieDtoReportResponse toDtoReport(String name, String description, Long timesRented, Double totalRevenue);

}
