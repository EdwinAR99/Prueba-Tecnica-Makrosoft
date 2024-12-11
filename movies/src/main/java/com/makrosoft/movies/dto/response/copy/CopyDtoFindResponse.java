package com.makrosoft.movies.dto.response.copy;

import com.makrosoft.movies.dto.response.movie.MovieDtoFindResponse;
import com.makrosoft.movies.model.CopyStatusEnum;
import lombok.*;

import java.time.LocalDateTime;
/**
 * Class that defines an entity for the search response in the COPY table.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CopyDtoFindResponse {

    /** Copy id */
    private Integer id;

    /** Copy unique code */
    private String code;

    /** Copy status */
    private CopyStatusEnum status;

    /** Genre create time */
    private LocalDateTime createTime;

    /** Genre create User */
    private String createUser;

    /** Movie last update time */
    private LocalDateTime updateTime;

    /** Movie last update user */
    private String updateUser;

    /** Movie of the copy */
    private MovieDtoFindResponse movie;

}
