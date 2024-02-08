package hu.progmasters.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostInfo {

    private Long id;

    private String dateOfPost;

    private String postTitle;

    private String postBody;

    private String postStatus;

    private Boolean commentStatus;

    private String tag;

    private Boolean highlighted;

    private CategoryInfo categoryInfo;
}
