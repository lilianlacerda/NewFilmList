package com.liliSolution.filmListNew.listFilm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liliSolution.filmListNew.listFilm.entity.Filme;

//Jpa repository serve para realizar operações de CRUD (Create, Read, Update, Delete) em entidades JPA
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    boolean existsByTitulo(String titulo);
}
