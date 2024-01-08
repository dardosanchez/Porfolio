package com.portfolio_backend.services;

import com.portfolio_backend.models.Proyecto;
import com.portfolio_backend.models.dto.ProyectoDTO;


import java.util.List;

public interface IProyectService {
    public Proyecto crearProyecto(ProyectoDTO proyectoDTO) throws Exception;

    public List<Proyecto> getProyectos ();

    public Proyecto getProyecto (Long id);

    public Proyecto editProyecto(Long id , ProyectoDTO proyectoDTO) throws Exception;

    public void deleteProyecto (Long id);


}
