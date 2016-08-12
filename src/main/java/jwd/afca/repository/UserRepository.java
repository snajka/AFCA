package jwd.afca.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.afca.model.User;

@Repository
public interface UserRepository 
	extends JpaRepository<User, Long> {

	Page<User> findByUsernameContains(Pageable page, String search);

	User findByUsernameAndEnabledTrue(String username);	

}
