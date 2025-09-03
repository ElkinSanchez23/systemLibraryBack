package com.example.demo.service;


import com.example.demo.entity.Libro;
import com.example.demo.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LibroService {

    private final LibroRepository repo;

    public LibroService(LibroRepository repo) {
        this.repo = repo;
    }

    public List<Libro> listar() {
        return repo.findAll();
    }

    public Optional<Libro> buscarPorId(Long id) {
        return repo.findById(id);
    }

    public Libro guardar(Libro libro) {
        return repo.save(libro);
    }

    public Libro actualizar(Long id, Libro libro) {
        return repo.findById(id).map(l -> {
            l.setTitulo(libro.getTitulo());
            l.setAutor(libro.getAutor());
            l.setGenero(libro.getGenero());
            l.setDisponible(libro.isDisponible());
            return repo.save(l);
        }).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
