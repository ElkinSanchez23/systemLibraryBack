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
public class Usuario {
@Id @GeneratedValue
    private Long id;
    @Valid
    @NotNull
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @Valid
    @NotNull
    @NotBlank(message = "El email no puede estar vacío")
    private String email;
}

