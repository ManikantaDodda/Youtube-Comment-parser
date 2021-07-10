package com.spacey.insta.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.spacey.insta.exception.UserNotFoundException;
import com.spacey.insta.response.StatusResponse;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	FollowersRepository followersRepository;

	public Userr fetchUser(String username) {
		Userr found = userRepository.findByUsername(username);
		if (found == null)
			throw new UserNotFoundException("user does not exist");
		return found;
	}

	public Userr createUser(Userr user) {
		return userRepository.save(user);
	}

	public StatusResponse deleteUser(String username) {
		try {
			userRepository.delete(username);
		} catch (EmptyResultDataAccessException e) {
			throw new UserNotFoundException(e.getMessage());
		}
		return new StatusResponse("success");
	}

	public StatusResponse followUser(String followedBy, String following) {
		Userr from = userRepository.findOne(followedBy);
		Userr to = userRepository.findOne(following);
		if (from != null && to != null) {
			followersRepository.save(new Followers(from, to));
			return new StatusResponse("success");
		} else {
			throw new UserNotFoundException("user(s) doesn't exist!");
		}
	}

	public StatusResponse updateUser(Userr user, String username) {
		// TIPS: to make a bulky changes:
		// repository.findById(id).map( entity -> { //do something return
		// repository.save(entity) } ).orElseGet( () -> { //do something return; })
		// .......END.......//
		// can't do the below commented one due to the fact:
		// https://stackoverflow.com/questions/21018728/jpa-hibernate-changing-the-primary-key-of-an-persisted-object
		// userRepository.updateUsername(user.username, username);
		// so, better delete the old one and then create another as follows:
		Userr existing = userRepository.findOne(username);
		if (existing != null) deleteUser(username);
		existing.setUsername(user.username);
		userRepository.save(existing);
		return new StatusResponse("success");
	}

}
