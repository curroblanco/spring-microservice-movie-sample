package com.movie.example.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.example.core.entity.Movie;

@Repository
public interface MovieJpaRepository extends JpaRepository<Movie, Long>{

}
