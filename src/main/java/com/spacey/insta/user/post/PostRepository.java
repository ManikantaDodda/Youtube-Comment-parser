package com.spacey.insta.user.post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUsername(String username);
	
	@Transactional
	@Modifying
	@Query("update Post p set p.upvotes = p.upvotes + 1 where p.postId = ?1")
	void updateUpvotes(Long pid);

}
