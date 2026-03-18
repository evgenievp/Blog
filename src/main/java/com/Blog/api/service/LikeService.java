package com.Blog.api.service;

import com.Blog.api.dto.LikeDto;
import com.Blog.api.dto.UserDto;
import com.Blog.api.model.Like;
import com.Blog.api.model.Post;
import com.Blog.api.model.Users;
import com.Blog.api.repo.AuthRepo;
import com.Blog.api.repo.LikeRepo;
import com.Blog.api.repo.PostRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {

    private final LikeRepo likeRepo;
    private final PostRepo postRepo;
    private final AuthRepo authRepo;

    public void likeOrUnlike(int userId, int postId) {
        Optional<Users> opt = this.authRepo.findById(userId);
        Optional<Post> optPost = this.postRepo.findById(postId);
        if (opt.isEmpty() || opt.isEmpty()) {
            throw new EntityNotFoundException("user or post is missing");
        }
        Users user = opt.get();
        Post post = optPost.get();;
        Like like = new Like();
        like.setPost(post);
        like.setUser(user);
        this.likeRepo.save(like);
    }

    public List<Like> getAllLikes() {
        return (List<Like>) this.likeRepo.findAll().stream().map(this::toDto);
    }


    public Integer getLikesCount(int postId) {
        return this.likeRepo.countByPost(postId);
    }

    public LikeDto toDto(Like like) {
        return new LikeDto(
                like.getPost(),
                like.getUser(),
                like.getCreatedAt()
        );
    }
    public Like toLike(LikeDto dto) {
        return new Like(
                dto.getPost(),
                dto.getUser(),
                dto.getCreatedAt()
        );
    }

    public List<UserDto> getUsersWhoLiked(int postId) {
        return this.postRepo.findAllById(postId);
    }
}