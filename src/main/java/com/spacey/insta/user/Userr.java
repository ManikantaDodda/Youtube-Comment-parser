package com.spacey.insta.user;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.spacey.insta.user.post.Post;

@Entity
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(value = { "followers", "following", "posts" }, allowGetters = true) // if you want to serialize
																							// properties but ignore
																							// while deserializing it
public class Userr {
	@Id
	String username;
	@ElementCollection
	@OneToMany(mappedBy = "to", cascade = CascadeType.ALL)
	List<Followers> followers;
	@ElementCollection
	@OneToMany(mappedBy = "from", cascade = CascadeType.ALL)
	List<Followers> following;
	// last two cascase will make sure that: deletion of Userr should also delete
	// all of this user entries from the followers table by considering all of which
	// whose 'froms' and 'tos' of Followers compares equal to this Userr.
	@ElementCollection
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "username")
	List<Post> posts;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getFollowers() {
		if (followers == null)
			return null;
		return followers.stream().map(f -> f.getFrom().username).collect(Collectors.toList());
	}

	public void setFollowers(List<Followers> followers) {
		this.followers = followers;
	}

	public List<String> getFollowing() {
		if (following == null)
			return null;
		return following.stream().map(f -> f.getTo().username).collect(Collectors.toList());
	}

	public void setFollowing(List<Followers> following) {
		this.following = following;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "Userr [username=" + username + ", followers=" + followers + ", following=" + following + ", posts="
				+ posts + "]";
	}

}
