package com.liliSolution.filmListNew.listFilm.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmeDTO {
    @NotBlank
    private String titulo;
    @NotBlank
    private String genero;
    private String diretor;
    private String anoLancamento;
    private String sinopse;   
}
