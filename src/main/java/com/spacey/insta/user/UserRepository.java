package com.spacey.insta.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Userr, String> {

	Userr findByUsername(String username);

}
