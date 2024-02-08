package hu.progmasters.backend.service;

import hu.progmasters.backend.config.DalleGeneratorConfig;
import hu.progmasters.backend.dto.GenerateImageRequest;
import hu.progmasters.backend.dto.GenerateImageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "dalleGenerator", url = "${openai.image-generator.url}", configuration = DalleGeneratorConfig.class)
public interface DalleGeneratorClient {

    @PostMapping(value = "/v1/images/generations")
    GenerateImageResponse generateImage(@RequestBody final GenerateImageRequest request);

}