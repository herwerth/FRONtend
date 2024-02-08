package hu.progmasters.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class GenerateImageResponse {

    private List<GeneratedImage> data;

}