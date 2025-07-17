package com.liliSolution.filmListNew.listFilm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "filme")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String genero;
    private String diretor;
    private String anoLancamento;
    private String sinopse;
    
    public Long getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getGenero() {
        return genero;
    }
    public String getSinopse() {
        return sinopse;
    }
    public String getDiretor() {
        return diretor;
    }
    public String getAnoLancamento() {
        return anoLancamento;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }
    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }
    public void setAnoLancamento(String anoLancamento) {
        this.anoLancamento = anoLancamento;
    }
}
