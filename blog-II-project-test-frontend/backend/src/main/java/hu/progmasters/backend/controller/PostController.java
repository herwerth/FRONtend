package hu.progmasters.backend.controller;

import hu.progmasters.backend.dto.CreatePostCommand;
import hu.progmasters.backend.dto.PostInfo;
import hu.progmasters.backend.dto.UpdatePostCommand;
import hu.progmasters.backend.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/posts")
@Slf4j
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/{accountId}/{categoryId}")
    public ResponseEntity<PostInfo> savePost(@PathVariable("accountId") Long accountId, @PathVariable("categoryId") Long categoryId, @Valid @RequestBody CreatePostCommand command) {
        log.info("Http request POST / /api/posts/{accountId}/{categoryId} with data: " + command.toString());
        PostInfo postInfo = postService.savePost(accountId, categoryId, command);
        return new ResponseEntity<>(postInfo, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}/{categoryId}")
    public ResponseEntity<PostInfo> updatePost(@PathVariable("postId") Long postId, @PathVariable("categoryId") Long categoryId, @Valid @RequestBody UpdatePostCommand command) {
        log.info("Http request PUT / /api/posts/{postId}/{categoryId} with data: " + command.toString());
        PostInfo postInfo = postService.updatePost(postId, categoryId, command);
        return new ResponseEntity<>(postInfo, HttpStatus.OK);
    }
}
