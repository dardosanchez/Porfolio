package com.portfolio_backend.controllers;

import com.portfolio_backend.models.Profile;
import com.portfolio_backend.models.dto.ProfileDTO;
import com.portfolio_backend.services.ProfileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
@CrossOrigin(value = "http://localhost:5173")
public class ProfileController {

    @Autowired
    private ProfileServices profileServices;

    @PostMapping("/create")
    public ResponseEntity<Profile> guardarPerfil (@ModelAttribute ProfileDTO profileDTO) throws Exception {

        Profile perfil = profileServices.guardarPerfil(profileDTO);
        return ResponseEntity.status(HttpStatus.OK).body(perfil);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Profile>> getProfiles (){
        return ResponseEntity.status(HttpStatus.OK).body(profileServices.getProfiles());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Profile> editProfile (@PathVariable Long id, @ModelAttribute ProfileDTO profileDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(profileServices.editProfile(id,profileDTO));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProfile (@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(profileServices.deleteProfile(id));
    }

}
