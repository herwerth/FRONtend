package hu.progmasters.backend.controller;

import hu.progmasters.backend.dto.GenerateImageRequest;
import hu.progmasters.backend.dto.GenerateImageResponse;
import hu.progmasters.backend.service.DalleGeneratorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/images")
@AllArgsConstructor
@Slf4j
public class DalleGeneratorController {
    private static final Logger LOG = LoggerFactory.getLogger(DalleGeneratorController.class);

    private final DalleGeneratorService dalleGeneratorService;

    @PostMapping("generate")
    public ResponseEntity<GenerateImageResponse> generateImage(@Valid @RequestBody final GenerateImageRequest request) {
        LOG.info("Received image generation request: {}", request);
        final GenerateImageResponse response = dalleGeneratorService.generateImage(request);
        LOG.info("Generated image: {}", response);
        return ResponseEntity.ok(response);
    }
}