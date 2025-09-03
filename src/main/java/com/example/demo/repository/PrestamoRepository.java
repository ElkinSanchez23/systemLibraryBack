package com.example.demo.repository;

import com.example.demo.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository <Prestamo, Long> { }
