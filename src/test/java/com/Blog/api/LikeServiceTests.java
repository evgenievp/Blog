package com.Blog.api;

import com.Blog.api.dto.UserDto;
import com.Blog.api.model.Like;
import com.Blog.api.model.Post;
import com.Blog.api.model.Users;
import com.Blog.api.repo.AuthRepo;
import com.Blog.api.repo.LikeRepo;
import com.Blog.api.repo.PostRepo;
import com.Blog.api.service.AuthService;
import com.Blog.api.service.LikeService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LikeServiceTests {

    @Mock
    private LikeRepo likeRepo;

    @Mock
    private AuthRepo authRepo;

    @Mock
    private AuthService authService;

    @Mock
    private PostRepo postRepo;

    @InjectMocks
    private LikeService likeService;

    private final String username = "ivan";
    private final int postId = 1;

    @Test
    public void testSuccessfulLike() {

        Users user = new Users();
        user.setUsername(username);

        Post post = new Post();
        post.setId(postId);

        when(authRepo.findByUsername(username)).thenReturn(Optional.of(user));
        when(postRepo.findById(postId)).thenReturn(Optional.of(post));
        when(likeRepo.findByPostAndUser(post, user)).thenReturn(Optional.empty());

        likeService.likeOrUnlike(username, postId);

        verify(likeRepo, times(1)).save(any(Like.class));
    }

    @Test
    public void testUnlikeWhenLikeExists() {

        Users user = new Users();
        Post post = new Post();
        Like existingLike = new Like();

        when(authRepo.findByUsername(username)).thenReturn(Optional.of(user));
        when(postRepo.findById(postId)).thenReturn(Optional.of(post));
        when(likeRepo.findByPostAndUser(post, user)).thenReturn(Optional.of(existingLike));

        likeService.likeOrUnlike(username, postId);

        verify(likeRepo, times(1)).delete(existingLike);
        verify(likeRepo, never()).save(any());
    }

    @Test
    public void testLikeShouldThrowWhenUserNotFound() {
        when(authRepo.findByUsername("nobody")).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            likeService.likeOrUnlike("nobody", 1);
        });
    }

    @Test
    public void testLikeCounterShouldCountCorrectly() {
        Users user = new Users();
        user.setUsername("Ivan");

        Users user1 = new Users();
        user1.setUsername("Ivaylo");

        List<Users> users = List.of(user, user1);

        when(postRepo.findUsersByPostId(postId)).thenReturn(users);
        when(authService.toDto(user)).thenReturn(new UserDto("Ivan"));
        when(authService.toDto(user1)).thenReturn(new UserDto("Ivaylo"));

        List<UserDto> result = likeService.getUsersWhoLiked(postId);

        assertEquals(2, result.size());
    }

    @Test
    public void testLikesCountShouldBeOk() {
        Post post = new Post();
        when(postRepo.getReferenceById(postId)).thenReturn(post);
        when(likeRepo.countByPost(post)).thenReturn(3);
        assertEquals(3, this.likeService.getLikesCount(postId));
    }


}