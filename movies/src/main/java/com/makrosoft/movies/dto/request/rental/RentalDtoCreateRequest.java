package com.makrosoft.movies.dto.request.rental;

import lombok.*;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
/**
 * Class that defines an entity for the input of information for creating a rental.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RentalDtoCreateRequest {

    /** Rental rental date */
    @NotNull(message = "{rental.rentalDate.field.not.null}")
    private LocalDateTime rentalDate;

    /** Rental due date */
    @NotNull(message = "{rental.dueDate.field.not.null}")
    private LocalDateTime dueDate;

    /** Rental customer id */
    @NotNull(message = "{rental.customer.field.not.null}")
    @NotEmpty(message = "{rental.customer.field.not.empty}")
    private String customer;

    /** Rental copy id */
    //@NotNull(message = "{rental.copy.field.not.null}")
    //@Min(value = 1, message = "{rental.copy.field.positive}")
    //private Integer copyId;
}