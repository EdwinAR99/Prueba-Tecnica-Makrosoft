package com.makrosoft.movies.dto.request.movie;

import lombok.*;

import jakarta.validation.constraints.*;
/**
 * Class that defines an entity for the input of information for creating a movie.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MovieDtoCreateRequest {

    /** Movie name */
    @NotNull(message = "{movie.name.field.not.null}")
    @NotEmpty(message = "{movie.name.field.not.empty}")
    @Size(max = 150, message = "{movie.name.field.size}")
    private String name;

    /** Movie description */
    @Size(max = 500, message = "{movie.description.field.size}")
    private String description;

    /** Movie genre id */
    @NotNull(message = "{movie.genre.field.not.null}")
    @Min(value = 1, message = "{movie.genre.field.positive}")
    private Integer genreId;
}