package com.example.sbfs.service;

import com.example.sbfs.entity.DestinationImage;
import com.example.sbfs.repo.DestinationImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DestinationImageService {
    private final DestinationImageRepository repository;
    private final String STORAGE_LOCATION = "/home/artemis/Desktop/DestinationImages/";
    public String uploadFile(MultipartFile file) throws IOException {
        String filePath = STORAGE_LOCATION+file.getOriginalFilename();
        DestinationImage destinationImage = repository.save(
                DestinationImage.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .filePath(filePath)
                        .build()
        );
        file.transferTo(new File(filePath));
        return "File uploaded successfully";
    }
    public byte[] downloadImage(String fileName) throws IOException {
        Optional<DestinationImage> fileData = repository.findByName(fileName);
        String filePath = fileData.get().getFilePath();
        return Files.readAllBytes(new File(filePath).toPath());
    }
}
