package com.makrosoft.movies.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.makrosoft.movies.dto.request.rental.RentalDtoCreateRequest;
import com.makrosoft.movies.dto.request.rental.RentalDtoUpdateRequest;
import com.makrosoft.movies.dto.response.rental.RentalDtoCreateResponse;
import com.makrosoft.movies.service.IRentalService;
import com.makrosoft.movies.util.response.Response;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Controller for managing rental-related endpoints.
 */
@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
@Validated
public class RentalController {

    private final IRentalService rentalService;

    /**
     * Endpoint to rent a movie.
     * 
     * @param movieId the id of the movie to be rented
     * @param rentalRequest rental information
     * @return the rental record
     */
    @PostMapping("/rent/{movieId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Response<RentalDtoCreateResponse>> rentMovie(@PathVariable Integer movieId, @RequestBody @Valid RentalDtoCreateRequest rentalRequest) {
        return new ResponseEntity<>(this.rentalService.rentMovie(movieId, rentalRequest), HttpStatus.OK);
    }

    /**
     * Endpoint to return a movie.
     * 
     * @param copyId the id of the copy to be returned
     * @param rentalRequest rental information
     * @return the rental record
     */
    @PutMapping("/return/{copyId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Response<RentalDtoCreateResponse>> renturnMovie(@PathVariable Integer copyId, @RequestBody @Valid RentalDtoUpdateRequest rentalRequest) {
        return new ResponseEntity<>(this.rentalService.returnMovie(copyId, rentalRequest), HttpStatus.OK);
    }


}
