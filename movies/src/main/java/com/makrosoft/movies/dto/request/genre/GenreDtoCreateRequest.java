package com.makrosoft.movies.dto.request.genre;

import lombok.*;

import jakarta.validation.constraints.*;

/**
 * Class that defines an entity for the input of information for creating a genre.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class GenreDtoCreateRequest {

    /** Genre name */
    @NotNull(message = "{genre.name.field.not.null}")
    @NotEmpty(message = "{genre.name.field.not.empty}")
    @Size(max = 100, message = "{genre.name.field.size}")
    private String name;

    /** Genre rental price */
    @NotNull(message = "{genre.rentalPrice.field.not.null}")
    @DecimalMin(value = "0.0", inclusive = false, message = "{genre.rentalPrice.field.positive}")
    private Double rentalPrice;
}
