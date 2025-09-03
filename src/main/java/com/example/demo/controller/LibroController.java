package com.example.demo.controller;

import com.example.demo.entity.Libro;
import com.example.demo.repository.LibroRepository;
import com.example.demo.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")

public class LibroController {

    @Autowired
    private LibroRepository libroRepository;


    @GetMapping
    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }


    @GetMapping("/{id}")
    public Libro getLibroById(@PathVariable Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con id " + id));
    }


    @PostMapping
    public Libro createLibro(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }


    @PatchMapping("/{id}")
    public Libro patchLibro(@PathVariable Long id, @RequestBody Libro libroActualizado) {
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
    public String deleteLibro(@PathVariable Long id) {
        return libroRepository.findById(id).map(libro -> {
            libroRepository.delete(libro);
            return "Libro con id " + id + " eliminado correctamente";
        }).orElseThrow(() -> new RuntimeException("Libro no encontrado con id " + id));
    }
}