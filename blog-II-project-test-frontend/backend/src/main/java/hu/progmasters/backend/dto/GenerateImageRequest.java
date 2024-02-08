package hu.progmasters.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import hu.progmasters.backend.domain.ValidSize;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
@Data
public class GenerateImageRequest {

    @NotBlank
    private String prompt;

    @ValidSize
    private String size;

    @Min(1)
    @Max(10)
    @JsonProperty("num_images")
    private int numImages;

}