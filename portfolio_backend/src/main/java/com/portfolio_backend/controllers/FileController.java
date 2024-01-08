package com.portfolio_backend.controllers;

import com.portfolio_backend.models.File;
import com.portfolio_backend.models.Response;
import com.portfolio_backend.services.FileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/file")
@CrossOrigin(value = "http://localhost:5173")
public class FileController {

    @Autowired
    private FileServices fileServices;

    @PostMapping("/upload")
    public ResponseEntity<Response> cargarFile(@RequestParam("files") MultipartFile file) throws Exception {
        String message = "";
        fileServices.save(file);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Los archivos fueron cargados correctamente al servidor"));
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getFile (@PathVariable String filename) throws Exception {
        Resource resource = fileServices.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/all")
    public ResponseEntity<List<File>> getAllFiles() throws Exception {
        List<File> files = fileServices.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FileController.class, "getFile", path.getFileName().toString()).build().toString();

            return new File(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

}
