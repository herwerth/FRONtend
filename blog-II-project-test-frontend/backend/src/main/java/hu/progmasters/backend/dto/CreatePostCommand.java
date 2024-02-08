package hu.progmasters.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CreatePostCommand {

    @NotNull
    @NotEmpty
    @NotBlank(message = "A poszt címe nem lehet üres!")
    @Size(min = 5, message = "A poszt címének legalább 5 karakternek kell lennie!")
    private String postTitle;

    @NotNull
    @NotEmpty
    @NotBlank(message = "A poszt nem lehet üres!")
    @Size(min = 5, message = "A posztnak legalább 5 karakternek kell lennie!")
    private String postBody;

    private String tag;

    private Boolean highlighted;
}
