package com.makrosoft.movies.util.response;

import lombok.*;

import java.util.List;
/**
 * Class that defines a pageable response
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageableResponse<T>{

        /** Data */
        private List<T> data;

        /** Pagination Page number */
        private int pageNo;

        /** Pagination Page size */
        private int pageSize;

        /** Pagination total elementes found */
        private long totalElements;

        /** Pagination total pages found */
        private long totalPages;

        /** if last page */
        private boolean last;
}
