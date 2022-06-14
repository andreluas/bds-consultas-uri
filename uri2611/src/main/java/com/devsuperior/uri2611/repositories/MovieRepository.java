package com.devsuperior.uri2611.repositories;

import java.util.List;

import com.devsuperior.uri2611.dto.MovieMinDTO;
import com.devsuperior.uri2611.entities.Movie;
import com.devsuperior.uri2611.projections.MovieMinProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    // SQL NATIVE
    @Query(nativeQuery = true, value = "SELECT movies.id, movies.name FROM movies INNER JOIN genres ON movies.id_genres = genres.id WHERE genres.description = :genreName")

    List<MovieMinProjection> searchMovieList(String genreName);

    // JPQL
    @Query("SELECT new com.devsuperior.uri2611.dto.MovieMinDTO(obj.id, obj.name) "
            + "FROM Movie obj "
            + "WHERE obj.genre.description = :genreName")

    List<MovieMinDTO> searchMovieListJPQL(String genreName);
}
