package com.makrosoft.movies.dto.response.movie;

import com.makrosoft.movies.dto.response.copy.CopyDtoFindResponse;
import com.makrosoft.movies.dto.response.genre.GenreDtoFindResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
/**
 * Class that defines an entity for the search response in the MOVIE table.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MovieDtoFindResponse {

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

    /** List of copies for the movie */
    private List<CopyDtoFindResponse> copies;

}
