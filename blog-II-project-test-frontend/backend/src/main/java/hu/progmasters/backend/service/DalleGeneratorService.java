package hu.progmasters.backend.service;

import hu.progmasters.backend.dto.GenerateImageRequest;
import hu.progmasters.backend.dto.GenerateImageResponse;

public interface DalleGeneratorService {

    GenerateImageResponse generateImage(GenerateImageRequest generateImageRequest);

}