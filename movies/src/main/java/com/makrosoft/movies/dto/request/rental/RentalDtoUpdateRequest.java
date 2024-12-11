package com.makrosoft.movies.dto.request.rental;

import lombok.*;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
/**
 * Class that defines an entity for the input of information for creating a rental.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RentalDtoUpdateRequest {

    /** Rental id */
    @NotNull(message = "{rental.ID.field.not.null}")
    @Positive(message = "{rental.ID.field.positive}")
    private Integer id;

    /** Rental rental date */
    private LocalDateTime rentalDate;

    /** Rental due date */
    private LocalDateTime dueDate;

    /** Rental return date */
    @NotNull(message = "{rental.returnDate.field.not.null}")
    private LocalDateTime returnDate;

    /** Rental customer id */
    private String customer;

    /** Rental copy id */
    @NotNull(message = "{rental.copy.field.not.null}")
    @Min(value = 1, message = "{rental.copy.field.positive}")
    private Integer copyId;

}
