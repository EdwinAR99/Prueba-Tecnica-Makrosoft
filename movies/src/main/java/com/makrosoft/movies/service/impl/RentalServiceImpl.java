package com.makrosoft.movies.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.makrosoft.movies.dto.response.rental.RentalDtoCreateResponse;
import com.makrosoft.movies.exception.BusinessRuleException;
import com.makrosoft.movies.mapper.IRentalMapper;
import com.makrosoft.movies.model.*;
import com.makrosoft.movies.repository.ICopyRepository;
import com.makrosoft.movies.repository.IMovieRepository;
import com.makrosoft.movies.repository.IRentalRepository;
import com.makrosoft.movies.service.IRentalService;
import com.makrosoft.movies.util.response.Response;
import com.makrosoft.movies.util.response.handler.ResponseHandler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements IRentalService {

    private final IMovieRepository movieRepository;
    private final ICopyRepository copyRepository;
    private final IRentalRepository rentalRepository;
    private final IRentalMapper rentalMapper;

    /**
     * @see IRentalService#rentMovie(Integer)
     */
    @Override
    public Response<RentalDtoCreateResponse> rentMovie(Integer movieId) {
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
        Rental rental = Rental.builder()
            .rentalDate(LocalDateTime.now())
            .dueDate(LocalDateTime.now().plusDays(7))
            .amountCharged(movieFound.get().getGenre().getRentalPrice())
            .createTime(LocalDateTime.now())
            .createUser("admin")
            .copy(availableCopy.get())
            .build();

        this.rentalRepository.save(rental);
        RentalDtoCreateResponse rentalDtoCreateResponse = rentalMapper.toDtoCreate(rental);
        return new ResponseHandler<>(200, "Copia rentada satisfactoriamente", "", rentalDtoCreateResponse).getResponse();
    }

    /**
     * @see IRentalService#returnMovie(Integer)
     */
    @Override
    public Response<RentalDtoCreateResponse> returnMovie(Integer copyId) {
        // Find the rental by ID
        Optional<Rental> rental = rentalRepository.findTopByCopyIdOrderByIdDesc(copyId);
        if (rental.isEmpty()) throw new BusinessRuleException("rental.request.not.found");
        if (rental.get().getReturnDate() != null) throw new BusinessRuleException("rental.return.date.is.full");
        if (rental.get().getCopy().getId() != copyId) throw new BusinessRuleException("rental.copy.not.equal");

        // Find the copy by ID
        Optional<Copy> availableCopy = this.copyRepository.findById(copyId);
        if (availableCopy.isEmpty()) throw new BusinessRuleException("copy.request.not.found");

        // Free the copy
        availableCopy.get().setStatus(CopyStatusEnum.DISPONIBLE);

        // Update the rental
        Rental updateRental = Rental.builder()
            .id(rental.get().getId())
            .rentalDate(rental.get().getRentalDate())
            .dueDate(rental.get().getDueDate())
            .returnDate(LocalDateTime.now())
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
