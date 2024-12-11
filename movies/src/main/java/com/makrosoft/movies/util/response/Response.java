package com.makrosoft.movies.util.response;

import lombok.*;

/**
 * Class that defines a generic DTO for the responses of requests to Rest services.
 * This encapsulates the result of the consumption of APIs and adds control attributes
 *
 * @param <T> Response object for the transactions carried out in each API
 *
 * @version 1.0
 */
@Getter @Setter @ToString
public class Response<T> {

    /** Indicates the http status of the transaction */
    private int status;

    /** Information message for the user */
    private String userMessage;

    /** Informational message for developers */
    private String developerMessage;

    /** Object with the transaction response */
    private T data;
}
