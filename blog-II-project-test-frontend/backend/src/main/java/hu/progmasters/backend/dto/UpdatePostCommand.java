package hu.progmasters.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatePostCommand {

    private String postTitle;

    private String postBody;

    private String tag;

    private Boolean highlighted;
}
