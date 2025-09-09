package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @Operation(summary = "Listar usuarios", description = "Devuelve la lista completa de usuarios registrados en el sistema")
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @PostMapping
    @Operation(summary = "Crear usuario", description = "Crea un nuevo usuario si no existe previamente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario creado o ya existente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public Usuario guardar(@RequestBody Usuario usuario) {
        return usuarioService.crearUsuarioSiNoExiste(usuario);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Buscar usuario por email", description = "Devuelve un usuario en base a su correo electrónico")
    public Optional<Usuario> buscarPorEmail(
            @Parameter(description = "Correo electrónico del usuario a buscar")
            @PathVariable String email) {
        return usuarioService.buscarPorEmail(email);
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(summary = "Buscar usuario por nombre", description = "Devuelve un usuario en base a su nombre")
    public Optional<Usuario> buscarPorNombre(
            @Parameter(description = "Nombre del usuario a buscar")
            @PathVariable String nombre) {
        return usuarioService.buscarPorNombre(nombre);
    }
}
