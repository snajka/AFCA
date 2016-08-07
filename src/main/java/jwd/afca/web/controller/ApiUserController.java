package jwd.afca.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.afca.model.User;
import jwd.afca.service.UserService;
import jwd.afca.support.UserDTOToUser;
import jwd.afca.support.UserRegistrationDTOToUser;
import jwd.afca.support.UserToUserDTO;
import jwd.afca.web.dto.UserDTO;
import jwd.afca.web.dto.UserRegistrationDTO;

@RestController
@RequestMapping(value = "/api/users")
public class ApiUserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserDTOToUser toUser;
	
	@Autowired
	private UserRegistrationDTOToUser toUserFromUserRegistration;

	@Autowired
	private UserToUserDTO toDTO;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<UserDTO>> getUser(
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "page", required = false, defaultValue="0") int page,
			@RequestParam(value = "itemsPerPage", required = false, defaultValue="5") int itemsPerPage,
			@RequestParam(value = "direction", required = false, defaultValue="DESC") String direction,
			@RequestParam(value = "property", required = false, defaultValue="id") String property) {

		List<User> users;
		Page<User> usersPage;
		int totalPages = 0;
		long totalElements = 0;
		
		if (search != null) {
			usersPage = userService.findByUsernameContains(page, itemsPerPage, search);
		} else {
			usersPage = userService.findAll(page, itemsPerPage, Sort.Direction.fromString(direction), property);
		}
		
		users = usersPage.getContent();
		totalPages = usersPage.getTotalPages();
		totalElements = usersPage.getTotalElements();
		
		if (page > totalPages) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("total-pages", Integer.toString(totalPages));
		httpHeaders.add("total-elements", Long.toString(totalElements));

		return new ResponseEntity<>(toDTO.convert(users), httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		User user = userService.findOne(id);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDTO.convert(user), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<UserDTO> add(@RequestBody UserRegistrationDTO newUser) {
		if (newUser.getPassword() == null || newUser.getPassword().isEmpty()
				|| !newUser.getPassword().equals(newUser.getPasswordConfirm())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		User savedUser = userService.save(toUserFromUserRegistration.convert(newUser));

		return new ResponseEntity<>(toDTO.convert(savedUser), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<UserDTO> edit(@RequestBody UserDTO user,
			@PathVariable Long id) {

		if (id != user.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		User persisted = userService.save(toUser.convert(user));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<User> delete(@PathVariable Long id) {
		userService.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
