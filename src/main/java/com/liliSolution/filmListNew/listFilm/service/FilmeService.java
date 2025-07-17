package com.liliSolution.filmListNew.listFilm.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.liliSolution.filmListNew.listFilm.DTO.FilmeDTO;
import com.liliSolution.filmListNew.listFilm.entity.Filme;
import com.liliSolution.filmListNew.listFilm.exception.MoovieDeleteException;
import com.liliSolution.filmListNew.listFilm.exception.MoovieNullException;
import com.liliSolution.filmListNew.listFilm.exception.MoovieTituloException;
import com.liliSolution.filmListNew.listFilm.repository.FilmeRepository;

@Service
public class FilmeService {

    private FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public Filme converterDTOParaFilme (FilmeDTO filmeDTO){
        Filme filme = new Filme();
        filme.setTitulo(filmeDTO.getTitulo());
        filme.setGenero(filmeDTO.getGenero());
        filme.setDiretor(filmeDTO.getDiretor());
        filme.setAnoLancamento(filmeDTO.getAnoLancamento());
        filme.setSinopse(filmeDTO.getSinopse());
        return filme;
    }

    public FilmeDTO converterFilmeParaDTO (Filme filme){
        FilmeDTO filmeDTO = new FilmeDTO();
        filmeDTO.setTitulo(filme.getTitulo());
        filmeDTO.setGenero(filme.getGenero());
        filmeDTO.setDiretor(filme.getDiretor());
        filmeDTO.setAnoLancamento(filme.getAnoLancamento());
        filmeDTO.setSinopse(filme.getSinopse());
        return filmeDTO;
    }

    public List<FilmeDTO> listarFilmes(){
        Sort sort = Sort.by("titulo").ascending();
        return filmeRepository.findAll(sort);
    }

    public Filme cadastrarFilme(Filme filme) throws Exception{
        if(filme.getTitulo() == null || filme.getGenero() == null){
            throw new MoovieNullException();
        }
        if(filmeRepository.existsByTitulo(filme.getTitulo())){
                throw new MoovieTituloException(); 
        }     
        return filmeRepository.save(filme);
    }

    public Filme editarFilme(Long id, Filme filme) {
        //optional serve para verificar se o filme existe
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
