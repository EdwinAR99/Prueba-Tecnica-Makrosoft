package com.makrosoft.movies.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.makrosoft.movies.dto.response.movie.MovieDtoAvailableResponse;
import com.makrosoft.movies.exception.BusinessRuleException;
import com.makrosoft.movies.mapper.IMovieMapper;
import com.makrosoft.movies.model.Movie;
import com.makrosoft.movies.repository.IMovieRepository;
import com.makrosoft.movies.service.impl.MovieServiceImpl;
import com.makrosoft.movies.util.response.PageableResponse;
import com.makrosoft.movies.util.response.Response;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @InjectMocks
    private MovieServiceImpl movieService;

    @Mock
    private IMovieMapper movieMapper;

    @Mock
    private IMovieRepository movieRepository;

    @Test
    void testSearchAvailableMovies() {
        // Datos de entrada
        int pageNumber = 0;
        int pageSize = 10;
        String query = "Inception";

        // Datos simulados para la prueba
        Movie movie1 = new Movie();
        movie1.setId(1);
        movie1.setName("Inception");
        movie1.setDescription("A mind-bending thriller");

        Movie movie2 = new Movie();
        movie2.setId(2);
        movie2.setName("Interstellar");
        movie2.setDescription("Space exploration");

        List<Movie> movieList = Arrays.asList(movie1, movie2);

        // Simulamos la respuesta del repositorio
        Page<Movie> moviePage = new PageImpl<>(movieList);

        // Simulamos el comportamiento del repositorio
        when(movieRepository.searchMoviesByNameOrDescription(query, PageRequest.of(pageNumber, pageSize)))
            .thenReturn(moviePage);

        // Simulamos el comportamiento del mapper para mapear las películas a DTOs
        when(movieMapper.toDtoAvailable(movie1)).thenReturn(
                new MovieDtoAvailableResponse(1, "Inception", "A mind-bending thriller", 5)
        );
        when(movieMapper.toDtoAvailable(movie2)).thenReturn(
                new MovieDtoAvailableResponse(2, "Interstellar", "Space exploration", 3)
        );

        // Ejecutamos el método del servicio
        Response<PageableResponse<Object>> response = movieService.searchAvailableMovies(pageNumber, pageSize, query);

        // Verificamos que el servicio haya devuelto la respuesta correcta
        assertEquals(200, response.getStatus());
        assertNotNull(response.getData());
        assertEquals(2, response.getData().getData().size());
        assertEquals("Inception", ((MovieDtoAvailableResponse) response.getData().getData().get(0)).getName());
        assertEquals(5, ((MovieDtoAvailableResponse) response.getData().getData().get(0)).getAvailableCopies());
        assertEquals("Interstellar", ((MovieDtoAvailableResponse) response.getData().getData().get(1)).getName());
        assertEquals(3, ((MovieDtoAvailableResponse) response.getData().getData().get(1)).getAvailableCopies());
    }

    @Test
    void testSearchAvailableMoviesNoResults() {
        // Datos de entrada
        int pageNumber = 0;
        int pageSize = 10;
        String query = "NonExistingMovie";

        // Simulamos una página vacía
        Page<Movie> moviePage = Page.empty();

        // Simulamos el comportamiento del repositorio para no encontrar resultados
        when(movieRepository.searchMoviesByNameOrDescription(query, PageRequest.of(pageNumber, pageSize)))
            .thenReturn(moviePage);

        // Ejecutamos el método del servicio y verificamos que lanza una excepción
        assertThrows(BusinessRuleException.class, () -> {
            movieService.searchAvailableMovies(pageNumber, pageSize, query);
        });
    }

}
