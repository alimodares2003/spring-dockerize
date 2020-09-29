package com.idco.mesghal.service;

import com.idco.mesghal.model.Post;
import com.idco.mesghal.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @MockBean
    private PostRepository postRepository;

    @Test
    @DisplayName("Get all posts")
    public void testGetPosts() {
        Post mockPost1 = new Post("Title test1", "Content test1");
        Post mockPost2 = new Post("Title test2", "Content test2");

        doReturn(Arrays.asList(mockPost1, mockPost2)).when(postRepository).findAll();

        List<Post> allPosts = postService.listAll();

        Assertions.assertEquals(2, allPosts.size());
    }

    @Test
    @DisplayName("Get specific post with id")
    public void testGetSpecificPost() {
        Post mockPost = new Post("Title test", "Content test");

        doReturn(mockPost).when(postRepository).findById(mockPost.getId());

        Post foundPost = postService.get(1L);

        Assertions.assertNotNull(foundPost);
        Assertions.assertSame("Title test", foundPost.getTitle());

    }

    @Test
    @DisplayName("Create new post")
    public void testSavePost() {
        Post mockPost = new Post("Title test", "Content test");

        doReturn(mockPost).when(postRepository).save(any());

        Post savedPost = postService.save(mockPost);

        Assertions.assertNotNull(savedPost);
        Assertions.assertSame("Title test", savedPost.getTitle());
        Assertions.assertSame("Content test", savedPost.getContent());
    }

    @Test
    @DisplayName("Update an existing post")
    public void testUpdatePost() {
        Post existingPost = new Post("Title test", "Content test");
        Post newPost = new Post("Title test new", "Content test new");

        doReturn(existingPost).when(postRepository).findById(existingPost.getId());
        doReturn(newPost).when(postRepository).save(existingPost);

        Post updatePost = postService.update(existingPost, existingPost.getId());

        Assertions.assertEquals("Title test new", updatePost.getTitle());
    }

    @Test
    @DisplayName("Delete post")
    public void testDeletePost() {
        Post mockPost = new Post("Title test", "Content test");
        Post mockPost2 = new Post("Title test2", "Content test2");

        doNothing().when(postRepository).deleteById(mockPost.getId());

        postService.delete(1L);

        Assertions.assertEquals(0, postRepository.count());
    }
}
