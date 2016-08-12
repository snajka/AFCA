package jwd.afca.service;

import java.util.Map;

import org.springframework.data.domain.Sort.Direction;

import jwd.afca.web.dto.UserDTO;
import jwd.afca.web.dto.UserRegistrationDTO;

public interface UserService {

	UserDTO findOne(Long id);
	Map<String, Object> findAll(int page, int itemsPerPage, Direction direction, String property);
	UserDTO save(UserDTO user);
	UserDTO save(UserRegistrationDTO user); 
	
	//za korisnika se u ovom primeru (bez
	//specijalnog razloga) koristi
	//varijanta brisanja koja NE vraća entitet
	void delete(Long id);
	Map<String, Object> findByUsernameContains(int page, int itemsPerPage, String search);

	UserDTO authenticateUser(String username, String password);
	UserDTO findByUsername(String username);
	
}
