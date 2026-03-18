package com.Blog.api.service;

import com.Blog.api.dto.PostDto;
import com.Blog.api.model.Post;
import com.Blog.api.model.Users;
import com.Blog.api.repo.AuthRepo;
import com.Blog.api.repo.PostRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class PostService {

    private final PostRepo postRepo;
    private final AuthRepo authRepo;

    public PostService(PostRepo postRepo, AuthRepo authRepo) {
        this.postRepo = postRepo;
        this.authRepo = authRepo;
    }

    public PostDto findById(int postId) {
        Optional<Post> opt = this.postRepo.findById(postId);
        if (opt.isEmpty()) {
            throw new EntityNotFoundException("no such post");
        }
        return toDto(opt.get());
    }

    public PostDto toDto(Post post) {
        return new PostDto(
                post.getAuthor(),
                post.getLikes()
        );
    }

    public Post toPost(PostDto dto) {
        return new Post(
                dto.getAuthor(),
                dto.getLikes()
        );
    }

    public PostDto post(int authorId, PostDto postDto) {
        Optional<Users> opt = this.authRepo.findById(authorId);
        if (opt.isEmpty()) {
            throw new EntityNotFoundException("no such user");
        }
        Users user = opt.get();;
        Post post = toPost(postDto);
        post.setAuthor(user);
        this.postRepo.save(post);
        PostDto answer = toDto(post);
        return answer;
    }
}
