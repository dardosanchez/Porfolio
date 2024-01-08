package com.portfolio_backend.services;

import com.portfolio_backend.models.Profile;
import com.portfolio_backend.models.dto.ProfileDTO;
import com.portfolio_backend.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.FileAlreadyExistsException;
import java.util.List;

@Service
public class ProfileServices implements IProfileService{

    @Autowired
    private ProfileRepository repoProfile;
    @Autowired
    private FileServices fileServices;

    @Override
    public Profile guardarPerfil(ProfileDTO profileDTO) throws Exception {

        String urlImagen;
        try {
            fileServices.save(profileDTO.getImagen());
            urlImagen = fileServices.getFileUrl(profileDTO.getImagen().getOriginalFilename());
        } catch (FileAlreadyExistsException e) {
            // Si el archivo ya existe, buscarlo y configurar la URL en el DTO
            urlImagen = fileServices.getFileUrl(profileDTO.getImagen().getOriginalFilename());
        }

        String urlImagenAbout;
        try {
            fileServices.save(profileDTO.getImageAbout());
            urlImagenAbout = fileServices.getFileUrl(profileDTO.getImageAbout().getOriginalFilename());
        } catch (FileAlreadyExistsException e) {
            // Si el archivo ya existe, buscarlo y configurar la URL en el DTO
            urlImagenAbout = fileServices.getFileUrl(profileDTO.getImageAbout().getOriginalFilename());
        }

        String urlCurriculum;
        try {
            fileServices.save(profileDTO.getCurriculum());
            urlCurriculum = fileServices.getFileUrl(profileDTO.getCurriculum().getOriginalFilename());
        } catch (FileAlreadyExistsException e) {
            // Si el archivo ya existe, buscarlo y configurar la URL en el DTO
            urlCurriculum = fileServices.getFileUrl(profileDTO.getCurriculum().getOriginalFilename());
        }

        Profile perfil = new Profile();
        perfil.setName(profileDTO.getName());
        perfil.setEmail(profileDTO.getEmail());
        perfil.setCargo(profileDTO.getCargo());
        perfil.setImagen(urlImagen);
        perfil.setImageAbout(urlImagenAbout);
        perfil.setGithub(profileDTO.getGithub());
        perfil.setLinkedin(profileDTO.getLinkedin());
        perfil.setCurriculum(urlCurriculum);

        return repoProfile.save(perfil);
    }

    @Override
    public Profile getProfile(Long id) {

        return repoProfile.findById(id).orElse(null);
    }

    @Override
    public List<Profile> getProfiles() {
        return repoProfile.findAll();
    }

    @Override
    public Profile editProfile(Long id, ProfileDTO profileDTO) throws Exception {

        Profile perfil = this.getProfile(id);

        String urlImagen;
        try {
            fileServices.save(profileDTO.getImagen());
            urlImagen = fileServices.getFileUrl(profileDTO.getImagen().getOriginalFilename());
        } catch (FileAlreadyExistsException e) {
            // Si el archivo ya existe, buscarlo y configurar la URL en el DTO
            urlImagen = fileServices.getFileUrl(profileDTO.getImagen().getOriginalFilename());
        }


        String urlImagenAbout;
        try {
            fileServices.save(profileDTO.getImageAbout());
            urlImagenAbout = fileServices.getFileUrl(profileDTO.getImageAbout().getOriginalFilename());
        } catch (FileAlreadyExistsException e) {
            // Si el archivo ya existe, buscarlo y configurar la URL en el DTO
            urlImagenAbout = fileServices.getFileUrl(profileDTO.getImageAbout().getOriginalFilename());
        }


        String urlCurriculum;
        try {
            fileServices.save(profileDTO.getCurriculum());
            urlCurriculum = fileServices.getFileUrl(profileDTO.getCurriculum().getOriginalFilename());
        } catch (FileAlreadyExistsException e) {
            // Si el archivo ya existe, buscarlo y configurar la URL en el DTO
            urlCurriculum = fileServices.getFileUrl(profileDTO.getCurriculum().getOriginalFilename());
        }

        perfil.setName(profileDTO.getName());
        perfil.setCargo(profileDTO.getCargo());
        perfil.setEmail(profileDTO.getEmail());
        perfil.setImagen(urlImagen);
        perfil.setImageAbout(urlImagenAbout);
        perfil.setGithub(profileDTO.getGithub());
        perfil.setLinkedin(profileDTO.getLinkedin());
        perfil.setCurriculum(urlCurriculum);

        return repoProfile.save(perfil);
    }

    @Override
    public String deleteProfile(Long id) {

        repoProfile.deleteById(id);
        return "Perfil " + id + " eliminado correctamente";

    }
}
