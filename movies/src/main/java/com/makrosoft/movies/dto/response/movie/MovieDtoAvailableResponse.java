package com.makrosoft.movies.dto.response.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * DTO for representing available movies with their free copy count.
 */
@Data
@Builder
@AllArgsConstructor
public class MovieDtoAvailableResponse {

    /** Movie id */
    private Integer id;

    /** Movie name */
    private String name;

    /** Movie description */
    private String description;

    /** Number of available copies */
    private Integer availableCopies;
}
