package com.spacey.insta.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<Userr, String> {

	Userr findByUsername(String username);
	
	// bad practice to update PK. keeping it just for the warning.
	@Transactional
	@Modifying
	@Query("update Userr u set u.username = ?1 where u.username = ?2")
	void updateUsername(String newname, String oldname);

}
