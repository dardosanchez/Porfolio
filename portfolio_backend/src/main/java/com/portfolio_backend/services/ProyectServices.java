package com.portfolio_backend.services;

import com.portfolio_backend.models.Proyecto;
import com.portfolio_backend.models.dto.ProyectoDTO;
import com.portfolio_backend.repository.ProyectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.FileAlreadyExistsException;
import java.util.List;


@Service
public class ProyectServices implements IProyectService {

    @Autowired
    private ProyectRepository repoProyecto;
    @Autowired
    private FileServices fileServices;

    @Override
    public Proyecto crearProyecto(ProyectoDTO proyectoDTO) throws Exception {

        String url;
        try {
            fileServices.save(proyectoDTO.getImagen());
            url = fileServices.getFileUrl(proyectoDTO.getImagen().getOriginalFilename());
        } catch (FileAlreadyExistsException e) {
            // Si el archivo ya existe, buscarlo y configurar la URL en el DTO
            url = fileServices.getFileUrl(proyectoDTO.getImagen().getOriginalFilename());
        }


        Proyecto proyecto = new Proyecto();
        proyecto.setNombre(proyectoDTO.getNombre());
        proyecto.setImagen(url);
        proyecto.setGithub(proyectoDTO.getGithub());
        proyecto.setLiveDemo(proyectoDTO.getLiveDemo());
        return repoProyecto.save(proyecto);
    }

    @Override
    public List<Proyecto> getProyectos() {

        return repoProyecto.findAll();
    }

    @Override
    public Proyecto getProyecto(Long id) {
        return repoProyecto.findById(id).orElse(null);
    }

    @Override
    public Proyecto editProyecto(Long id , ProyectoDTO proyectoDTO) throws Exception {
        Proyecto proyecto = this.getProyecto(id);

        String url;
        try {
            fileServices.save(proyectoDTO.getImagen());
            url = fileServices.getFileUrl(proyectoDTO.getImagen().getOriginalFilename());
        } catch (FileAlreadyExistsException e) {
            // Si el archivo ya existe, buscarlo y configurar la URL en el DTO
            url = fileServices.getFileUrl(proyectoDTO.getImagen().getOriginalFilename());
        }

        proyecto.setNombre(proyectoDTO.getNombre());
        proyecto.setImagen(url);
        proyecto.setGithub(proyectoDTO.getGithub());
        proyecto.setLiveDemo(proyectoDTO.getLiveDemo());

        return repoProyecto.save(proyecto);
    }

    @Override
    public void deleteProyecto(Long id) {
        repoProyecto.deleteById(id);
    }
}
