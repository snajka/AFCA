package jwd.afca.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;

import jwd.afca.model.User;

public interface UserService {

	User findOne(Long id);
	Page<User> findAll(int page, int itemsPerPage, Direction direction, String property);
	User save(User user);
	
	//za korisnika se u ovom primeru (bez
	//specijalnog razloga) koristi
	//varijanta brisanja koja NE vraća entitet
	void delete(Long id);
	Page<User> findByUsernameContains(int page, int itemsPerPage, String search); 
	
}
