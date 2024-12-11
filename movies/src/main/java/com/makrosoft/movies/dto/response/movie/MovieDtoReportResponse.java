package com.makrosoft.movies.dto.response.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class MovieDtoReportResponse {

    /** Movie name */
    private String name;

    /** Movie description */
    private String description;

    /** Times rented */
    private Long timesRented;

    /** Total rental revenue */
    private Double totalRevenue;

}
