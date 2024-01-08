package com.portfolio_backend.controllers;

import com.portfolio_backend.models.Proyecto;
import com.portfolio_backend.models.dto.ProyectoDTO;
import com.portfolio_backend.services.ProyectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/proyect")
@CrossOrigin(value = "http://localhost:5173")
public class ProyectController {

    @Autowired
    private ProyectServices proyectServices;

    @PostMapping("/upload")
    public ResponseEntity<Proyecto> cargarProyecto (@ModelAttribute ProyectoDTO proyecto) throws Exception {

        Proyecto proyect = proyectServices.crearProyecto(proyecto);

        return ResponseEntity.status(HttpStatus.OK).body(proyect);
    }

    @GetMapping("/all")
    public List<Proyecto> traerProyectos () {
        return proyectServices.getProyectos();
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<Proyecto> editarProyecto (@PathVariable Long id, @ModelAttribute ProyectoDTO proyectoDto) throws Exception {

        Proyecto proyecto = proyectServices.editProyecto(id,proyectoDto);
        return ResponseEntity.status(HttpStatus.OK).body(proyecto);
    }


    @DeleteMapping("/delete/{id}")
    public String eliminarProyecto (@PathVariable Long id) {
        proyectServices.deleteProyecto(id);
        return "Proyecto eliminado correctamente";
    }
}
