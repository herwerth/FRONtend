package hu.progmasters.backend.controller;

import hu.progmasters.backend.service.CloudinaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cloudinary")
@Slf4j
public class CloudinaryController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public ResponseEntity upload(@RequestParam("file") MultipartFile file) {
        try {
            Map uploadResult = cloudinaryService.upload(file);
            return ResponseEntity.ok(uploadResult);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Hiba történt a kép feltöltésekor.");
        }
    }

    @GetMapping("/list")
    public ResponseEntity listFiles() {
        try {
            List<Map> filesList = cloudinaryService.listFiles();
            return ResponseEntity.ok(filesList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Hiba történt a fájlok listázásakor");
        }
    }

    @DeleteMapping("delete/{publicId}")
    public ResponseEntity deleteImage(@PathVariable String publicId) {
        Map result = cloudinaryService.deleteImage(publicId);
        if (result != null) {
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.status(500).body("Hiba történt a kép törlésekor");
        }
    }
}
