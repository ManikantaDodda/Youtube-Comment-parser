package com.spacey.insta.user.post;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
//@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(value = { "upvotes" }, allowGetters = true)
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long postId;
	String imageUrl;
	String caption;
	int upvotes;
	@JsonIgnore
	String username;

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public int getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", imageUrl=" + imageUrl + ", caption=" + caption + ", upvotes=" + upvotes
				+ ", username=" + username + "]";
	}

}
