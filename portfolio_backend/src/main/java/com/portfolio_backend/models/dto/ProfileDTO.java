package com.portfolio_backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {

    private String name;
    private String cargo;
    private String email;
    private MultipartFile imagen;
    private MultipartFile imageAbout;
    private String github;
    private String linkedin;
    private MultipartFile curriculum;

}
