package com.example.demo.controller;

import com.example.demo.entity.Prestamo;
import com.example.demo.service.PrestamoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
@Tag(name = "Préstamos", description = "Operaciones relacionadas con los préstamos de libros")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    @Operation(summary = "Listar préstamos", description = "Devuelve la lista de todos los préstamos (activos y devueltos)")
    public List<Prestamo> listar() {
        return prestamoService.listar();
    }

    @PostMapping("/prestar/{libroId}/{usuarioEmail}")
    @Operation(
            summary = "Registrar un préstamo",
            description = "Asigna un libro a un usuario si está disponible"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Préstamo registrado correctamente"),
            @ApiResponse(responseCode = "400", description = "El libro no está disponible o el usuario no existe")
    })
    public Prestamo prestar(
            @Parameter(description = "ID del libro a prestar") @PathVariable Long libroId,
            @Parameter(description = "Email del usuario que solicita el libro") @PathVariable String usuarioEmail
    ) {
        return prestamoService.prestarLibro(libroId, usuarioEmail);
    }

    @PostMapping("/devolver/{prestamoId}")
    @Operation(
            summary = "Devolver un libro",
            description = "Marca un préstamo como devuelto"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Préstamo actualizado como devuelto"),
            @ApiResponse(responseCode = "404", description = "El préstamo no existe")
    })
    public Prestamo devolver(
            @Parameter(description = "ID del préstamo a devolver") @PathVariable Long prestamoId
    ) {
        return prestamoService.devolverLibro(prestamoId);
    }
}
