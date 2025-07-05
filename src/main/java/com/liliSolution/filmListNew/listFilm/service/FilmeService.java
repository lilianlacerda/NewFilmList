package com.liliSolution.filmListNew.listFilm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.liliSolution.filmListNew.listFilm.entity.Filme;
import com.liliSolution.filmListNew.listFilm.repository.FilmeRepository;

@Service
public class FilmeService {

    private FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public List<Filme> ListarFilmes(){
        Sort sort = Sort.by("titulo").ascending();
        return filmeRepository.findAll(sort);
    }

    public List<Filme> EditarFilme(Long id, Filme filme) {
        Optional<Filme> filmeExistente = filmeRepository.findById(id);
        
        if(filmeExistente.isPresent()){
            filme.setId(id);
            filmeRepository.save(filme);
        }

        return ListarFilmes();
    }

    public List<Filme> DeletarFilme(Long id){
        Optional<Filme> filmeExistente = filmeRepository.findById(id);
        
        if(filmeExistente.isPresent()){
            filmeRepository.deleteById(id);
        }

        return ListarFilmes();
    }
    
}
