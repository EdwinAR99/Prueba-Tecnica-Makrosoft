package com.makrosoft.movies.mapper;

import org.mapstruct.*;

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
@Mapper(componentModel = "spring", uses = {IGenreMapper.class, ICopyMapper.class})
public interface IMovieMapper {

    /**
     * Converts a Movie entity to a MovieDtoCreateResponse.
     *
     * @param movie The Movie entity to convert.
     * @return The corresponding MovieDtoCreateResponse.
     */
    MovieDtoCreateResponse toDtoCreate(final Movie movie);

    /**
     * Converts a Movie entity to a MovieDtoFindResponse.
     *
     * @param movie The Movie entity to convert.
     * @return The corresponding MovieDtoFindResponse.
     */
    MovieDtoFindResponse toDtoFind(final Movie movie);

    /**
     * Converts a MovieDtoCreateRequest to a Movie entity, ignoring certain fields.
     *
     * @param movieDtoCreateRequest The MovieDtoCreateRequest to convert.
     * @return The corresponding Movie entity.
     */
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "createTime", ignore = true),
        @Mapping(target = "createUser", ignore = true),
        @Mapping(target = "updateTime", ignore = true),
        @Mapping(target = "updateUser", ignore = true),
        @Mapping(target = "genre", ignore = true),
        @Mapping(target = "copies", ignore = true)
    })
    Movie toEntityCreate(final MovieDtoCreateRequest movieDtoCreateRequest);

    /**
     * Converts a Movie entity to a MovieDtoAvailableResponse, including the available copies count.
     *
     * @param movie The Movie entity to convert.
     * @return The corresponding MovieDtoAvailableResponse with available copies count.
     */
    @Mappings(
        @Mapping(target = "availableCopies", expression = "java(getAvailableCopies(movie))")
    )
    MovieDtoAvailableResponse toDtoAvailable(Movie movie);

    /**
     * Custom method to calculate the number of available copies for a given movie.
     *
     * @param movie The Movie entity to check for available copies.
     * @return The number of available copies.
     */
    default int getAvailableCopies(Movie movie) {
        return (int) movie.getCopies().stream()
                .filter(copy -> copy.getStatus().equals(CopyStatusEnum.DISPONIBLE))
                .count();
    }

    /**
     * Converts movie rental statistics into a MovieDtoReportResponse.
     *
     * @param name         The movie's name.
     * @param description  The movie's description.
     * @param timesRented  The total number of times the movie has been rented.
     * @param totalRevenue The total revenue generated from the movie's rentals.
     * @return The corresponding MovieDtoReportResponse with the rental statistics.
     */
    @Mappings({
        @Mapping(target = "name", source = "name"),
        @Mapping(target = "description", source = "description"),
        @Mapping(target = "timesRented", source = "timesRented"),
        @Mapping(target = "totalRevenue", source = "totalRevenue")
    })
    MovieDtoReportResponse toDtoReport(String name, String description, Long timesRented, Double totalRevenue);

}
