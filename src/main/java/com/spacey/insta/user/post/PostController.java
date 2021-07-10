package com.spacey.insta.user.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spacey.insta.exception.UserNotFoundException;
import com.spacey.insta.response.StatusResponse;

@RestController
@RequestMapping("api/")
public class PostController {

	@Autowired
	PostService postService;

	@PostMapping("/create-post/{username}")
	public Post createPost(@RequestBody Post post, @PathVariable("username") String username) {
		return postService.createPost(post, username);
	}

	@GetMapping("/all-posts/{username}")
	public List<Post> getAllPosts(@PathVariable("username") String username) {
		return postService.getAllPosts(username);
	}

	@PutMapping("/post/{pid}/upvote")
	public StatusResponse upvotePost(@PathVariable("pid") Long pid) {
		return postService.upvotePost(pid);
	}
	
	@ExceptionHandler(UserNotFoundException.class) // (optional to list exceptions here) takes an exception or a
													// list of exceptions as an argument that
													// we want to handle in the defined method
	@ResponseStatus(code = HttpStatus.BAD_REQUEST) // Also, the annotation @ResponseStatus(HttpStatus.NOT_FOUND) on the
													// handler method is not required as the HTTP status passed into the
													// ResponseEnity will take precedence, but we have kept it anyway
													// for the same readability reasons.
	public ResponseEntity<StatusResponse> handleUserNotFoundException(UserNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StatusResponse("failure", exception.getMessage()));
	}

}
