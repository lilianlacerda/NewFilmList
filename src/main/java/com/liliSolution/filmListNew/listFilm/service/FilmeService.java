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

    public List<Filme> listarFilmes(){
        Sort sort = Sort.by("titulo").ascending();
        return filmeRepository.findAll(sort);
    }

    public Filme cadastrarFilme(Filme filme){
        return filmeRepository.save(filme);
    }

    public Filme editarFilme(Long id, Filme filme) {
        Optional<Filme> filmeExistente = filmeRepository.findById(id);
        
        if(filmeExistente.isPresent()){
            filme.setId(id);
            filmeRepository.save(filme);
        }

        return filme;
    }

    public void deletarFilme(Long id){
        Optional<Filme> filmeExistente = filmeRepository.findById(id);
        
        if(filmeExistente.isPresent()){
            filmeRepository.deleteById(id);
        }
    }
    
}
