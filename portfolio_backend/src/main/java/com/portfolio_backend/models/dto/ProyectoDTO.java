package com.portfolio_backend.models.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProyectoDTO {

    private String nombre;
    private MultipartFile imagen;
    private String imageAbout;
    private String github;
    private String liveDemo;

}
