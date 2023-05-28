package com.example.sbfs.controller;

import com.example.sbfs.service.DestinationImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class DestinationImageController {
    private final DestinationImageService service;
    @PostMapping("/upload")
    ResponseEntity<?> uploadFile(@RequestParam("image")MultipartFile file) throws IOException {
        String image = service.uploadFile(file);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }
    @GetMapping("/{name}")
    ResponseEntity<?> downloadImage(@PathVariable String name) throws IOException {
        byte[] imageData = service.downloadImage(name);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageData);
    }
}
