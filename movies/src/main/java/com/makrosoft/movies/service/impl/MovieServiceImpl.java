package com.makrosoft.movies.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.makrosoft.movies.mapper.IMovieMapper;
import com.makrosoft.movies.model.Movie;
import com.makrosoft.movies.repository.IMovieRepository;
import com.makrosoft.movies.service.IMovieService;
import com.makrosoft.movies.util.response.PageableResponse;
import com.makrosoft.movies.util.response.Response;
import com.makrosoft.movies.util.response.handler.ResponseHandler;

@Service
public class MovieServiceImpl implements IMovieService{

    private final IMovieRepository movieRepository;
    private final IMovieMapper movieMapper;

    public MovieServiceImpl(IMovieRepository movieRepository, IMovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }


    /**
     * @see IMovieService#searchAvailableMovies(Integer, Integer, String)
     */
    @Override
    public Response<PageableResponse<Object>> searchAvailableMovies(int pageNumber, int pageSize, String query) {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        // Fetch movies using the repository
        Page<Movie> page = movieRepository.searchMoviesByNameOrDescription(query, pageRequest);

        // Map the entities to DTOs
        List<Object> movieList = page.get().map(
                this.movieMapper::toDtoAvailable)
        .collect(Collectors.toList());

        // Build a paginated response
        PageableResponse<Object> response = PageableResponse.builder()
                .data(movieList)
                .pageNo(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .last(page.isLast())
                .build();

        System.out.println(query);
        System.out.println(response.getData());
        // Return the response using a handler
        return new ResponseHandler<>(200, "Movies found successfully", "", response).getResponse();
    }



}
