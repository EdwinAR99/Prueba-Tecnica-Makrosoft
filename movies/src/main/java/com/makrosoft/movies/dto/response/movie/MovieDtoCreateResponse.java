package com.makrosoft.movies.dto.response.movie;

import lombok.*;

import java.time.LocalDateTime;

import com.makrosoft.movies.dto.response.genre.GenreDtoFindResponse;
/**
 * Class that defines an entity for the creation response in the MOVIE table.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MovieDtoCreateResponse {

    /** Movie id */
    private Integer id;

    /** Movie name */
    private String name;

    /** Movie description */
    private String description;

    /** Movie genre */
    private GenreDtoFindResponse genre;

    /** Movie creation user */
    private String createUser;

    /** Movie creation time */
    private LocalDateTime createTime;

    /** Movie last update user */
    private String updateUser;

    /** Movie last update time */
    private LocalDateTime updateTime;

}
