package com.example.demo.service;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public List<Usuario> listar() {
        return repo.findAll();
    }

    public Usuario crearUsuarioSiNoExiste(Usuario usuario) {
        return repo.findByEmail(usuario.getEmail())
                .orElseGet(() -> repo.save(usuario));
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return repo.findByEmail(email);
    }

    public Optional<Usuario> buscarPorNombre(String nombre) {
        return repo.findByNombre(nombre);
    }
}
