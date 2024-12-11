package com.makrosoft.movies.service;

import com.makrosoft.movies.dto.response.rental.RentalDtoCreateResponse;
import com.makrosoft.movies.util.response.Response;

/**
 * Service interface for managing movie rentals.
 */
public interface IRentalService {

    /**
     * Rents a movie by updating the status of an available copy and creating a rental record.
     *
     * @param movieId The ID of the movie to be rented.
     * @return A response containing the created rental record.
     */
    Response<RentalDtoCreateResponse> rentMovie(Integer movieId);

    /**
     * Returns a movie by updating the status of the copy and marking it as DISPONIBLE.
     *
     * @param copyId The ID of the copy to be returned.
     * @return A response containing the updated rental record.
     */
    Response<RentalDtoCreateResponse> returnMovie(Integer copyId);

}
