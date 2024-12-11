package com.makrosoft.movies.dto.response.rental;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Class that defines an entity for the creation response in the RENTAL table.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RentalDtoCreateResponse {

    /** Rental id */
    private Integer id;

    /** Rental rental date */
    private LocalDateTime rentalDate;

    /** Rental due date */
    private LocalDateTime dueDate;

    /** Rental return date */
    private LocalDateTime returnDate;

    /** Rental amount charged */
    private Double amountCharged;

    /** Rental create time */
    private LocalDateTime createTime;

    /** Rental create User */
    private String createUser;

    /** Customer id */
    private String customer;

    /** Copy id */
    //private Integer copyId;


}
