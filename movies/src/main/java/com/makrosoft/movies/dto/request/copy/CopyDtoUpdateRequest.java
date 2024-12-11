package com.makrosoft.movies.dto.request.copy;

import lombok.*;

import jakarta.validation.constraints.*;
import com.makrosoft.movies.model.CopyStatusEnum;
/**
 * Class that defines an entity for the input of information for updating a movie copy.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CopyDtoUpdateRequest {

    /** Copy id */
    @NotNull(message = "{copy.ID.field.not.null}")
    @Positive(message = "{copy.ID.field.positive}")
    private Integer id;

    /** Copy unique code */
    @NotNull(message = "{copy.code.field.not.null}")
    @NotEmpty(message = "{copy.code.field.not.empty}")
    @Size(max = 50, message = "{copy.code.field.size}")
    private String code;

    /** Copy status */
    @NotNull(message = "{copy.status.field.not.null}")
    private CopyStatusEnum status;

    /** Copy movie id */
    @NotNull(message = "{copy.movie.field.not.null}")
    @Min(value = 1, message = "{copy.movie.field.positive}")
    private Integer movieId;

}
