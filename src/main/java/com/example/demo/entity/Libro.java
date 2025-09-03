package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Libro {
    @Id @GeneratedValue
    private Long id;
    @Valid
    @NotNull
    @NotBlank(message = "El Titulo no puede estar vacío")
    private String titulo;
    @Valid
    @NotNull
    @NotBlank(message = "La casilla Autor no puede estar vacío")
    private String autor;
    @Valid
    @NotNull
    @NotBlank(message = "El genero no puede estar vacío")
    private String genero;
    @Valid
    @NotNull
    private boolean disponible = true;
}
