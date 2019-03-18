package com.movie.example.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.example.core.entity.Actor;

@Repository
public interface ActorJpaRepository extends JpaRepository<Actor, Long> {

}
