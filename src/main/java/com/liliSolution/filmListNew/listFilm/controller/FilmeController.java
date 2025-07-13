package com.liliSolution.filmListNew.listFilm.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liliSolution.filmListNew.listFilm.entity.Filme;
import com.liliSolution.filmListNew.listFilm.service.FilmeService;

@RestController
@RequestMapping("/filmes")
@CrossOrigin(origins = "http://127.0.0.1:5500/")
public class FilmeController {
    private FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Filme>> listarFilmes(){
        List<Filme> filmes = filmeService.listarFilmes();
        return ResponseEntity.ok().body(filmes);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Filme> cadastrarFilme(@RequestBody Filme filme) throws Exception{
        // FilmeNovo serve para armazenar o filme retornado após o cadastro
        // Isso é útil para retornar o filme com o ID gerado pelo banco de dados
        Filme filmeNovo = filmeService.cadastrarFilme(filme);
        return ResponseEntity.ok().body(filmeNovo);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Filme> editarFilme(@PathVariable Long id, @RequestBody Filme filme){
        // Verifica se o filme existe e o edita
        // Se não existir, o método editarFilme do serviço não vai funcioanr
        Filme filmeEditado = filmeService.editarFilme(id, filme);
        return ResponseEntity.ok().body(filmeEditado);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarFilme(@PathVariable Long id){
        filmeService.deletarFilme(id);
        return ResponseEntity.noContent().build();
    }
    
}
