package com.makrosoft.movies.model;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;
/**
 * Class that defines an entity for the O/R mapping of the COPY table.
 */
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Copy {

    /** Copy id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Copy unique code */
    @Column(length = 50, unique = true, nullable = false)
    private String code;

    /** Copy status */
    @Enumerated(EnumType.STRING)
    private CopyStatusEnum status;

    /** Copy create time */
    private LocalDateTime createTime;

    /** Copy create User */
    @Column(length = 250)
    private String createUser;

    /** Copy last update time */
    private LocalDateTime updateTime;

    /** Copy last update user */
    @Column(length = 250)
    private String updateUser;

    /** Copy movie */
    @ManyToOne(optional = false)
    @JoinColumn(name = "movieId")
    private Movie movie;
}
