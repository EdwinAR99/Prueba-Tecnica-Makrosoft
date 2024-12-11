package com.makrosoft.movies.model;

import lombok.*;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
/**
 * Class that defines an entity for the O/R mapping of the GENRE table.
 */
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Genre {

    /** Genre id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Genre name */
    @Column(length = 100, unique = true, nullable = false)
    private String name;

    /** Genre rental price */
    @Column(nullable = false)
    private Double rentalPrice;

    /** Genre create time */
    private LocalDateTime createTime;

    /** Genre create User */
    @Column(length = 250)
    private String createUser;

    /** Genre last update time */
    private LocalDateTime updateTime;

    /** Genre last update user */
    @Column(length = 250)
    private String updateUser;

    /** Genre movies */
    @OneToMany(mappedBy = "genre")
    private List<Movie> movies;
}
