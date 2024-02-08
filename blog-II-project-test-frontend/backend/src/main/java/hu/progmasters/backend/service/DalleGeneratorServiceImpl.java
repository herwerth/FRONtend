package hu.progmasters.backend.service;

import hu.progmasters.backend.dto.GenerateImageRequest;
import hu.progmasters.backend.dto.GenerateImageResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DalleGeneratorServiceImpl implements DalleGeneratorService {
    private final DalleGeneratorClient dalleGeneratorClient;

    public GenerateImageResponse generateImage(final GenerateImageRequest request) {
        final GenerateImageResponse response = dalleGeneratorClient.generateImage(request);
        return response;
    }
}