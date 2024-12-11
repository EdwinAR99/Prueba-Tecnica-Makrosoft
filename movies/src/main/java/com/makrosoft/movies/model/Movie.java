package com.makrosoft.movies.model;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
/**
 * Class that defines an entity for the O/R mapping of the MOVIE table.
 */
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Movie {
    /** Movie id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Movie name */
    @Column(length = 250, nullable = false)
    private String name;

    /** Movie description */
    @Column(length = 500)
    private String description;

    /** Movie create time */
    private LocalDateTime createTime;

    /** Movie create User */
    @Column(length = 250)
    private String createUser;

    /** Movie last update time */
    private LocalDateTime updateTime;

    /** Movie last update user */
    @Column(length = 250)
    private String updateUser;

    /** Movie Genre */
    @ManyToOne(optional = false)
    @JoinColumn(name = "genreId")
    private Genre genre;

    /** Movie Copy */
    @OneToMany(mappedBy = "movie")
    private List<Copy> copies;
}
