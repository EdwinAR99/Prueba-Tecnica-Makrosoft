package com.makrosoft.movies.service;

import com.makrosoft.movies.dto.request.rental.*;
import com.makrosoft.movies.dto.response.rental.RentalDtoCreateResponse;
import com.makrosoft.movies.util.response.Response;

public interface IRentalService {

    // TODO Cambiar metodo para que solo reciba el movieId
    /**
     * Rent a movie by updating a copy's status and creating a rental record.
     *
     * @param movieId the id of the movie to be rented
     * @param rentalRequest the rental details
     * @return the created rental record
     */
    Response<RentalDtoCreateResponse> rentMovie(Integer movieId, RentalDtoCreateRequest rentalRequest);

    /**
     * Return a movie by updating a copy's status and creating a available record.
     *
     * @param copyId the id of the copy to be returned
     * @param rentalRequest the rental details
     * @return the created rental record
     */
    Response<RentalDtoCreateResponse> returnMovie(Integer copyId, RentalDtoUpdateRequest rentalRequest);


    // TODO Implementar HU004 - Reporte de peliculas

}
