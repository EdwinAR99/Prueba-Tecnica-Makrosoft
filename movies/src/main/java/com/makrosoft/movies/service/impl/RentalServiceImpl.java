package com.makrosoft.movies.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.makrosoft.movies.dto.request.rental.*;
import com.makrosoft.movies.dto.response.rental.RentalDtoCreateResponse;
import com.makrosoft.movies.exception.BusinessRuleException;
import com.makrosoft.movies.mapper.IRentalMapper;
import com.makrosoft.movies.model.*;
import com.makrosoft.movies.repository.ICopyRepository;
import com.makrosoft.movies.repository.IMovieRepository;
import com.makrosoft.movies.repository.IRentalRepository;
import com.makrosoft.movies.service.IMovieService;
import com.makrosoft.movies.service.IRentalService;
import com.makrosoft.movies.util.response.Response;
import com.makrosoft.movies.util.response.handler.ResponseHandler;

@Service
public class RentalServiceImpl implements IRentalService {

    private final IMovieRepository movieRepository;
    private final ICopyRepository copyRepository;
    private final IRentalRepository rentalRepository;
    private final IRentalMapper rentalMapper;

    public RentalServiceImpl(IMovieRepository movieRepository, ICopyRepository copyRepository,
            IRentalRepository rentalRepository, IRentalMapper rentalMapper) {
        this.movieRepository = movieRepository;
        this.copyRepository = copyRepository;
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
    }

    /**
     * @see IRentalService#rentMovie(Integer, RentalDtoCreateRequest)
     */
    @Override
    public Response<RentalDtoCreateResponse> rentMovie(Integer movieId, RentalDtoCreateRequest rentalRequest) {
        // Find the movie by ID
        Optional<Movie> movieFound = this.movieRepository.findById(movieId);
        if (movieFound.isEmpty()) throw new BusinessRuleException("movie.request.not.found");

        // Find the first available copy of the movie
        Optional<Copy> availableCopy = this.copyRepository.findFirstByMovieAndStatus(movieFound.get(), CopyStatusEnum.DISPONIBLE);
        if (availableCopy.isEmpty()) throw new BusinessRuleException("copy.available.request.not.found");

        // Mark the copy as rented
        availableCopy.get().setStatus(CopyStatusEnum.ALQUILADA);
        this.copyRepository.save(availableCopy.get());

        // Create the rental record
        Rental rental = rentalMapper.toEntityCreate(rentalRequest);
        rental.setAmountCharged(movieFound.get().getGenre().getRentalPrice());
        rental.setCopy(availableCopy.get());
        rental.setCreateTime(LocalDateTime.now());

        this.rentalRepository.save(rental);
        RentalDtoCreateResponse rentalDtoCreateResponse = rentalMapper.toDtoCreate(rental);
        return new ResponseHandler<>(200, "Copia rentada satisfactoriamente", "", rentalDtoCreateResponse).getResponse();
    }

    /**
     * @see IRentalService#returnMovie(Integer, RentalDtoUpdateRequest)
     */
    @Override
    public Response<RentalDtoCreateResponse> returnMovie(Integer copyId, RentalDtoUpdateRequest rentalRequest) {
        // Find the rental by ID
        Optional<Rental> rental = rentalRepository.findById(rentalRequest.getId());
        if (rental.isEmpty()) throw new BusinessRuleException("rental.request.not.found");

        // Find the copy by ID
        Optional<Copy> availableCopy = this.copyRepository.findById(copyId);
        if (availableCopy.isEmpty()) throw new BusinessRuleException("copy.available.request.not.found");

        Rental updateRental = Rental.builder()
            .id(rentalRequest.getId())
            .rentalDate(rental.get().getRentalDate())
            .dueDate(rental.get().getDueDate())
            .returnDate(rentalRequest.getReturnDate())
            .amountCharged(rental.get().getAmountCharged())
            .createTime(rental.get().getCreateTime())
            .createUser(rental.get().getCreateUser())
            .updateTime(LocalDateTime.now())
            .updateUser("Usuario1")
            .customer(rental.get().getCustomer())
            .copy(availableCopy.get())
            .build();
        
        Rental rentalUpdated = this.rentalRepository.save(updateRental);
        RentalDtoCreateResponse rentalDtoCreateResponse = rentalMapper.toDtoCreate(rentalUpdated);

        return new ResponseHandler<>(200, "Renta actualizada exitosamente", "", rentalDtoCreateResponse).getResponse();

    }

}
