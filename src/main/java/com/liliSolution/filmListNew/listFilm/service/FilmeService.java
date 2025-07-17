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
        List<Filme> filmes = filmeRepository.findAll(sort);
        return filmes.stream()
                    .map(this::converterFilmeParaDTO)
                    .collect(Collectors.toList());
    }

    public Filme cadastrarFilme(FilmeDTO filmeDTO) throws Exception{
        if(filmeDTO.getTitulo() == null || filmeDTO.getGenero() == null){
            throw new MoovieNullException();
        }
        if(filmeRepository.existsByTitulo(filmeDTO.getTitulo())){
                throw new MoovieTituloException(); 
        } 
        Filme filme = converterDTOParaFilme(filmeDTO);    
        return filmeRepository.save(filme);
    }

    public FilmeDTO editarFilme(Long id, FilmeDTO filmeDTO) throws Exception {
        //optional serve para verificar se o filme existe
        Optional<Filme> filmeExistente = filmeRepository.findById(id);
        
        if(filmeExistente.isPresent()){
            Filme filme = filmeExistente.get();
            filme.setTitulo(filmeDTO.getTitulo());
            filme.setGenero(filmeDTO.getGenero());
            filme.setDiretor(filmeDTO.getDiretor());
            filme.setAnoLancamento(filmeDTO.getAnoLancamento());
            filme.setSinopse(filmeDTO.getSinopse());

            Filme filmeAtualizado = filmeRepository.save(filme);
            return converterFilmeParaDTO(filmeAtualizado);
        }

        throw new Exception();
    }

    public void deletarFilme(Long id) throws MoovieDeleteException{
        if(!filmeRepository.findById(id).isPresent()){
            throw new MoovieDeleteException();
        }

        filmeRepository.deleteById(id);
    }
    
}
