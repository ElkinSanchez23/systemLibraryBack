package com.example.demo.service;


import com.example.demo.entity.Libro;
import com.example.demo.entity.Prestamo;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.LibroRepository;
import com.example.demo.repository.PrestamoRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoService {
    private final PrestamoRepository prestamoRepo;
    private final LibroRepository libroRepo;
    private final UsuarioRepository usuarioRepo;

    public PrestamoService(PrestamoRepository prestamoRepo, LibroRepository libroRepo, UsuarioRepository usuarioRepo) {
        this.prestamoRepo = prestamoRepo;
        this.libroRepo = libroRepo;
        this.usuarioRepo = usuarioRepo;
    }

    public Prestamo prestarLibro(Long libroId, String usuarioEmail) {
        Libro libro = libroRepo.findById(libroId).orElseThrow();
        if (!libro.isDisponible()) throw new RuntimeException("Libro no disponible");

        Usuario usuario = usuarioRepo.findByEmail(usuarioEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Prestamo p = new Prestamo();
        p.setUsuario(usuario);
        p.setLibro(libro);
        p.setFechaPrestamo(LocalDate.now());
        p.setFechaDevolucion(LocalDate.now().plusDays(15));

        libro.setDisponible(false);
        libroRepo.save(libro);

        return prestamoRepo.save(p);
    }

    public Prestamo devolverLibro(Long prestamoId) {
        Prestamo p = prestamoRepo.findById(prestamoId).orElseThrow();
        Libro libro = p.getLibro();
        libro.setDisponible(true);
        libroRepo.save(libro);

        prestamoRepo.delete(p);
        return p;
    }

    public List<Prestamo> listar() { return prestamoRepo.findAll(); }
}
