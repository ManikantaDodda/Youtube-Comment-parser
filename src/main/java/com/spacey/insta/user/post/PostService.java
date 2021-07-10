package com.spacey.insta.user.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spacey.insta.exception.UserNotFoundException;
import com.spacey.insta.response.StatusResponse;
import com.spacey.insta.user.UserRepository;

@Service
public class PostService {
	@Autowired
	PostRepository postRepository;
	@Autowired
	UserRepository userRepository;

	public Post createPost(Post post, String username) {
		post.setUsername(username);
		if (userRepository.findByUsername(username) != null)
			return postRepository.save(post);
		else
			throw new UserNotFoundException("creation of post require a valid username");
	}

	public List<Post> getAllPosts(String username) {
		if (userRepository.findByUsername(username) != null)
			return postRepository.findByUsername(username);
		else
			throw new UserNotFoundException("fetching posts require a valid username");
	}

	public StatusResponse upvotePost(Long pid) {
		postRepository.updateUpvotes(pid);
		return new StatusResponse("success");
	}
}
