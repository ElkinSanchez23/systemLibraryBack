package com.example.demo.controller;

import com.example.demo.entity.Prestamo;
import com.example.demo.service.PrestamoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public List<Prestamo> listar() {
        return prestamoService.listar();
    }

    @PostMapping("/prestar/{libroId}/{usuarioEmail}")
    public Prestamo prestar(@PathVariable Long libroId, @PathVariable String usuarioEmail) {
        return prestamoService.prestarLibro(libroId, usuarioEmail);
    }

    @PostMapping("/devolver/{prestamoId}")
    public Prestamo devolver(@PathVariable Long prestamoId) {
        return prestamoService.devolverLibro(prestamoId);
    }
}
