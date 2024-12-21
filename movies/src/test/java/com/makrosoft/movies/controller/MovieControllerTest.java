package com.makrosoft.movies.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.makrosoft.movies.dto.response.movie.MovieDtoAvailableResponse;
import com.makrosoft.movies.dto.response.movie.MovieDtoFindResponse;
import com.makrosoft.movies.service.IMovieService;
import com.makrosoft.movies.util.response.PageableResponse;
import com.makrosoft.movies.util.response.Response;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IMovieService movieService;

    @Test
    @WithMockUser(username = "admin", password = "1245", roles = "ADMIN")
    public void testSearchAvailableMovies() throws Exception {
        // Datos de entrada
        String query = "Inception";
        int pageNumber = 0;
        int pageSize = 10;

        // Datos de prueba para la respuesta del servicio
        PageableResponse<Object> pageableResponse = new PageableResponse<>();
        pageableResponse.setData(Arrays.asList(
            new MovieDtoAvailableResponse(1, "Inception", "A mind-bending thriller", 5), // 5 copias disponibles
            new MovieDtoAvailableResponse(2, "Interstellar", "Space exploration", 3)  // 3 copias disponibles
        ));
        pageableResponse.setTotalElements(2);
        pageableResponse.setTotalPages(1);

        Response<PageableResponse<Object>> response = new Response<>();
        response.setData(pageableResponse);

        // Configuramos el mock del servicio para que devuelva la respuesta simulada
        when(movieService.searchAvailableMovies(pageNumber, pageSize, query)).thenReturn(response);

        // Realizamos la solicitud GET y verificamos la respuesta
        mockMvc.perform(MockMvcRequestBuilders.get("/movies/search")
                .param("pageNumber", String.valueOf(pageNumber))
                .param("pageSize", String.valueOf(pageSize))
                .param("query", query))
                .andExpect(status().isOk())  // Verificamos que el estado sea 200 OK
                .andExpect(jsonPath("$.data.data[0].name", is("Inception")))  // Verificamos que la primera película sea "Inception"
                .andExpect(jsonPath("$.data.data[1].name", is("Interstellar")))  // Verificamos la segunda película
                .andExpect(jsonPath("$.data.data[0].availableCopies", is(5)))  // Verificamos las copias disponibles para la primera película
                .andExpect(jsonPath("$.data.data[1].availableCopies", is(3)))  // Verificamos las copias disponibles para la segunda película
                .andExpect(jsonPath("$.data.totalElements", is(2)))  // Verificamos el número total de películas
                .andExpect(jsonPath("$.data.totalPages", is(1)));  // Verificamos el número total de páginas
    }

    @Test
    @WithMockUser(username = "admin", password = "1245", roles = "ADMIN")
    public void testSearchAvailableMoviesNoResults() throws Exception {
        // Datos de entrada para una búsqueda sin resultados
        String query = "NonExistingMovie";
        int pageNumber = 0;
        int pageSize = 10;

        // Creamos una respuesta vacía para simular no encontrar resultados
        PageableResponse<Object> pageableResponse = new PageableResponse<>();
        pageableResponse.setData(Collections.emptyList());
        pageableResponse.setTotalElements(0);
        pageableResponse.setTotalPages(0);

        Response<PageableResponse<Object>> response = new Response<>();
        response.setData(pageableResponse);

        // Configuramos el mock del servicio para que devuelva la respuesta vacía
        when(movieService.searchAvailableMovies(pageNumber, pageSize, query)).thenReturn(response);

        // Realizamos la solicitud GET y verificamos la respuesta
        mockMvc.perform(MockMvcRequestBuilders.get("/movies/search")
                .param("pageNumber", String.valueOf(pageNumber))
                .param("pageSize", String.valueOf(pageSize))
                .param("query", query))
                .andExpect(status().isOk())  // Verificamos que el estado sea 200 OK
                .andExpect(jsonPath("$.data.data", hasSize(0)))  // Verificamos que no haya contenido
                .andExpect(jsonPath("$.data.totalElements", is(0)))  // Verificamos que no haya resultados
                .andExpect(jsonPath("$.data.totalPages", is(0)));  // Verificamos que no haya páginas
    }

    @Test
    @WithMockUser(username = "admin", password = "1245", roles = "ADMIN")
    public void testGetAllMovies() throws Exception {
        // Datos de entrada
        int pageNumber = 0;
        int pageSize = 10;
        Integer id = null;
        String name = "Inception";
        String description = null;

        // Datos de prueba para la respuesta del servicio
        PageableResponse<Object> pageableResponse = new PageableResponse<>();
        pageableResponse.setData(Arrays.asList(new MovieDtoFindResponse()));
        pageableResponse.setTotalElements(1L);
        pageableResponse.setTotalPages(1);

        Response<PageableResponse<Object>> response = new Response<>();
        response.setData(pageableResponse);

        when(movieService.getAllMovies(pageNumber, pageSize, id, name, description)).thenReturn(response);

        // Ejecución y verificación
        mockMvc.perform(MockMvcRequestBuilders.get("/movies")
                .param("pageNumber", String.valueOf(pageNumber))
                .param("pageSize", String.valueOf(pageSize))
                .param("name", name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.totalElements", is(1)))
                .andExpect(jsonPath("$.data.data", hasSize(1)));
    }

    @Test
    @WithMockUser(username = "admin", password = "1245", roles = "ADMIN")
    public void testGetAllMoviesWithId() throws Exception {
        // Datos de entrada
        int pageNumber = 0;
        int pageSize = 10;
        Integer id = 1;
        String name = null;
        String description = null;

        // Datos de prueba para la respuesta del servicio
        PageableResponse<Object> pageableResponse = new PageableResponse<>();
        pageableResponse.setData(Arrays.asList(new MovieDtoFindResponse()));
        pageableResponse.setTotalElements(1L);
        pageableResponse.setTotalPages(1);

        Response<PageableResponse<Object>> response = new Response<>();
        response.setData(pageableResponse);

        when(movieService.getAllMovies(pageNumber, pageSize, id, name, description)).thenReturn(response);

        // Ejecución y verificación
        mockMvc.perform(MockMvcRequestBuilders.get("/movies")
                .param("pageNumber", String.valueOf(pageNumber))
                .param("pageSize", String.valueOf(pageSize))
                .param("id", String.valueOf(id)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.totalElements", is(1)))
                .andExpect(jsonPath("$.data.data", hasSize(1)));
    }

    @Test
    @WithMockUser(username = "admin", password = "1245", roles = "ADMIN")
    public void testGetAllMoviesWithDescription() throws Exception {
        // Datos de entrada
        int pageNumber = 0;
        int pageSize = 10;
        Integer id = null;
        String name = null;
        String description = "thriller";

        // Datos de prueba para la respuesta del servicio
        PageableResponse<Object> pageableResponse = new PageableResponse<>();
        pageableResponse.setData(Arrays.asList(new MovieDtoFindResponse()));
        pageableResponse.setTotalElements(1L);
        pageableResponse.setTotalPages(1);

        Response<PageableResponse<Object>> response = new Response<>();
        response.setData(pageableResponse);

        when(movieService.getAllMovies(pageNumber, pageSize, id, name, description)).thenReturn(response);

        // Ejecución y verificación
        mockMvc.perform(MockMvcRequestBuilders.get("/movies")
                .param("pageNumber", String.valueOf(pageNumber))
                .param("pageSize", String.valueOf(pageSize))
                .param("description", description))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.totalElements", is(1)))
                .andExpect(jsonPath("$.data.data", hasSize(1)));
    }
}
