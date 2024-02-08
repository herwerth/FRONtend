package hu.progmasters.backend.service;

import hu.progmasters.backend.domain.Account;
import hu.progmasters.backend.domain.Category;
import hu.progmasters.backend.domain.Post;
import hu.progmasters.backend.domain.Status;
import hu.progmasters.backend.dto.*;
import hu.progmasters.backend.exceptionhandling.PostNotFoundByIdException;
import hu.progmasters.backend.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Transactional
public class PostService {

    private PostRepository postRepository;

    private ModelMapper modelMapper;

    private AccountService accountService;

    private CategoryService categoryService;

    @Autowired
    public PostService(PostRepository postRepository, ModelMapper modelMapper, AccountService accountService, CategoryService categoryService) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.accountService = accountService;
        this.categoryService = categoryService;
    }

    public PostInfo savePost(Long accountId, Long categoryId, CreatePostCommand command) {
        Account account = accountService.findAccountById(accountId);
        Category category = categoryService.findCategoryById(categoryId);
        Post post = modelMapper.map(command, Post.class);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        post.setDateOfPost(dateTimeFormatter.format(LocalDateTime.now()));
        post.setPostStatus(Status.ACTIVE);
        post.setCommentStatus(true);
        post.setAccount(account);
        post.setCategory(category);
        postRepository.save(post);
        PostInfo postInfo = modelMapper.map(post, PostInfo.class);
        postInfo.setCategoryInfo(modelMapper.map(category, CategoryInfo.class));
        return postInfo;
    }

    public PostInfo updatePost(Long postId, Long categoryId, UpdatePostCommand command) {
        Post post = findPostById(postId);
        Category category = categoryService.findCategoryById(categoryId);
        post.setCategory(category);
        if (command.getPostTitle().isEmpty()) {
            command.setPostTitle(post.getPostTitle());
        }
        if (command.getPostBody().isEmpty()) {
            command.setPostBody(post.getPostBody());
        }
        modelMapper.map(command, post);
        PostInfo postInfo = modelMapper.map(post, PostInfo.class);
        postInfo.setCategoryInfo(modelMapper.map(post.getCategory(), CategoryInfo.class));
        return postInfo;
    }

    private Post findPostById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) {
            throw new PostNotFoundByIdException(id);
        }
        return optionalPost.get();
    }
}
