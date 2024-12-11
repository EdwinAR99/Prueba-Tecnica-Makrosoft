package com.makrosoft.movies.dto.response.genre;

import lombok.*;

import java.time.LocalDateTime;
/**
 * Class that defines an entity for the creation response in the GENRE table.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class GenreDtoCreateResponse {

    /** Genre id */
    private Integer id;

    /** Genre name */
    private String name;

    /** Genre rental price */
    private Double rentalPrice;

    /** Genre create time */
    private LocalDateTime createTime;

    /** Genre create User */
    private String createUser;

}
