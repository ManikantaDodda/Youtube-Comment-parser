package com.spacey.insta.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/get-user/{username}")
	public Userr fetchUser(@PathVariable("username") String username) {
		return userService.fetchUser(username);
	}

	@PostMapping("/create-user")
	public Userr createUser(@RequestBody Userr user) {
		return userService.createUser(user);
	}

	@PostMapping("/follow/{u1}/{u2}")
	public StatusResponse followUser(@PathVariable("u1") String followedBy, @PathVariable("u2") String following) {
		return userService.followUser(followedBy, following);
	}

	@DeleteMapping("/delete-user/{username}")
	public StatusResponse deleteUser(@PathVariable("username") String username) {
		return userService.deleteUser(username);
	}

	@PutMapping("/update-user/{username}")
	public StatusResponse updateUser(@RequestBody Userr user, @PathVariable("username") String username) {
		return userService.updateUser(user, username);
	}
	
	
	@ExceptionHandler(UserNotFoundException.class) // (optional to list exceptions here) takes an exception or a
													// list of exceptions as an argument that
													// we want to handle in the defined method
	@ResponseStatus(code = HttpStatus.NOT_FOUND) // Also, the annotation @ResponseStatus(HttpStatus.NOT_FOUND) on the
													// handler method is not required as the HTTP status passed into the
													// ResponseEnity will take precedence, but we have kept it anyway
													// for the same readability reasons.
	public ResponseEntity<StatusResponse> handleUserNotFoundException(UserNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StatusResponse("failure", exception.getMessage()));
	}
}
