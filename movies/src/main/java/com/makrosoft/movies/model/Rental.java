package com.makrosoft.movies.model;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;
/**
 * Class that defines an entity for the O/R mapping of the RENTAL table.
 */
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Rental {

    /** Rental id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(length = 250)
    private String createUser;

    /** Rental last update time */
    private LocalDateTime updateTime;

    /** Rental last update user */
    @Column(length = 250)
    private String updateUser;

    /** Rental customer */
    private String customer;

    /** Rental copy */
    @ManyToOne(optional = false)
    @JoinColumn(name = "copyId")
    private Copy copy;

}
