package com.portfolio_backend.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface IFileService {

    public void save(MultipartFile file) throws Exception;

    public Resource load (String name) throws Exception;

    public Stream<Path> loadAll() throws Exception;

    String getFileUrl(String filename) throws Exception;

}
