package com.movie.example.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.example.core.entity.Actor;

public interface ActorJpaRepository extends JpaRepository<Actor, Long> {

}
