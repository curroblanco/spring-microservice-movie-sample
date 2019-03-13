package com.movie.example.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.example.core.entity.Movie;

public interface MovieJpaRepository extends JpaRepository<Movie, Long>{

}
