package com.portfolio_backend.repository;

import com.portfolio_backend.models.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectRepository extends JpaRepository<Proyecto,Long> {
}
