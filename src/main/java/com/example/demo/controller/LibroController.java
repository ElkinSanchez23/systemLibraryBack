package com.example.demo.controller;

import com.example.demo.entity.Libro;
import com.example.demo.repository.LibroRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
@Tag(name = "Libros", description = "Operaciones CRUD para la gestión de libros")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    @GetMapping
    @Operation(summary = "Listar todos los libros", description = "Devuelve una lista con todos los libros registrados")
    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un libro por ID", description = "Busca un libro según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libro encontrado"),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    public Libro getLibroById(
            @Parameter(description = "ID del libro a buscar") @PathVariable Long id
    ) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con id " + id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo libro", description = "Registra un nuevo libro en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libro creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public Libro createLibro(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualizar parcialmente un libro", description = "Permite actualizar algunos campos de un libro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libro actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    public Libro patchLibro(
            @Parameter(description = "ID del libro a actualizar") @PathVariable Long id,
            @RequestBody Libro libroActualizado
    ) {
        return libroRepository.findById(id).map(libro -> {
            if (libroActualizado.getTitulo() != null) {
                libro.setTitulo(libroActualizado.getTitulo());
            }
            if (libroActualizado.getAutor() != null) {
                libro.setAutor(libroActualizado.getAutor());
            }
            if (libroActualizado.getGenero() != null) {
                libro.setGenero(libroActualizado.getGenero());
            }
            libro.setDisponible(libroActualizado.isDisponible());

            return libroRepository.save(libro);
        }).orElseThrow(() -> new RuntimeException("Libro no encontrado con id " + id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un libro", description = "Borra un libro de la base de datos por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libro eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    public String deleteLibro(
            @Parameter(description = "ID del libro a eliminar") @PathVariable Long id
    ) {
        return libroRepository.findById(id).map(libro -> {
            libroRepository.delete(libro);
            return "Libro con id " + id + " eliminado correctamente";
        }).orElseThrow(() -> new RuntimeException("Libro no encontrado con id " + id));
    }
}
