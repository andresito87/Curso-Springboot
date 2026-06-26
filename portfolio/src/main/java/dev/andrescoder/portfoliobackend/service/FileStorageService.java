package dev.andrescoder.portfoliobackend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload.dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file) throws IOException {

        // Verificamos si el archivo está vacío
        if (file.isEmpty()) {
            throw new IOException("File is empty");
        }

        // Obtenemos el nombre del archivo que sube el usuario
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        // Construimos nombre del archivo
        String fileName = UUID.randomUUID().toString() + extension;

        // Construimos la ruta donde se almacena el archivo en el servidor
        Path filePath = Paths.get(uploadDir, fileName).normalize();

        // Copia el archivo en la ruta del servidor
        Files.copy(file.getInputStream(), filePath);

        // Retornar la url de la URL relativa
        return "/img/projects/" + fileName;
    }

}
