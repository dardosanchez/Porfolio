package com.portfolio_backend.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileServices implements IFileService {

    private final Path rootFolder = Paths.get("uploads");

    @Override
    public void save(MultipartFile file) throws Exception {
        // Reemplaza espacios con guiones bajos
        String cleanFileName = file.getOriginalFilename().replaceAll("\\s+", "_");
        Files.copy(file.getInputStream(), this.rootFolder.resolve(cleanFileName));
    }

    @Override
    public Resource load(String name) throws Exception {
        // Reemplaza espacios con guiones bajos
        String cleanName = name.replaceAll("\\s+", "_");
        Path file = rootFolder.resolve(cleanName);
        Resource resource = new UrlResource(file.toUri());
        return resource;
    }

    @Override
    public Stream<Path> loadAll() throws Exception {

        return Files.walk(rootFolder,1).filter(path -> !path.equals(rootFolder)).map(rootFolder::relativize);

    }

    public String getFileUrl(String filename) throws Exception {
        // Reemplaza espacios con guiones bajos en el nombre del archivo
        String cleanFileName = filename.replaceAll("\\s+", "_");

        Path file = rootFolder.resolve(cleanFileName);

        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/{filename}")
                .buildAndExpand(cleanFileName).toUriString();
    }


}
