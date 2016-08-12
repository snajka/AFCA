package jwd.afca.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jwd.afca.model.User;
import jwd.afca.repository.UserRepository;
import jwd.afca.service.UserService;
import jwd.afca.support.UserDTOToUser;
import jwd.afca.support.UserRegistrationDTOToUser;
import jwd.afca.support.UserToUserDTO;
import jwd.afca.web.dto.UserDTO;
import jwd.afca.web.dto.UserRegistrationDTO;

@Service
public class JpaUserService implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDTOToUser toUser;
	
	@Autowired
	private UserRegistrationDTOToUser toUserFromUserRegistration;

	@Autowired
	private UserToUserDTO toDTO;

	@Override
	public UserDTO findOne(Long id) {
		return toDTO.convert(userRepository.findOne(id));
	}

	@Override
	public Map<String, Object> findAll(int page, int itemsPerPage, Sort.Direction direction, String property) {
		Page<User> usersPage = userRepository.findAll(new PageRequest(page, itemsPerPage, direction, property));
		Map<String, Object> map = new HashMap<>();
		map.put("totalPages", usersPage.getTotalPages());
		map.put("totalElements", usersPage.getTotalElements());
		map.put("dtos", toDTO.convert(usersPage));
		
		return map;
	}

	@Override
	public UserDTO save(UserDTO dto) {
		return toDTO.convert(userRepository.save(toUser.convert(dto)));
	}

	@Override
	public UserDTO save(UserRegistrationDTO dto) {
		return toDTO.convert(userRepository.save(toUserFromUserRegistration.convert(dto)));
	}
	
	@Override
	public void delete(Long id) {
		userRepository.delete(id);
	}

	@Override
	public Map<String, Object> findByUsernameContains(int page, int itemsPerPage, String search) {
		Page<User> usersPage = userRepository.findByUsernameContains(new PageRequest(page, itemsPerPage), search);
		Map<String, Object> map = new HashMap<>();
		map.put("totalPages", usersPage.getTotalPages());
		map.put("totalElements", usersPage.getTotalElements());
		map.put("dtos", toDTO.convert(usersPage));
		
		return map;
	}

	@Override
	public UserDTO authenticateUser(String username, String password) {
		UserDTO dto = null;
		User user = userRepository.findByUsernameAndEnabledTrue(username);
		if (user != null && user.getPassword().equals(password)) {
			dto = toDTO.convert(user);
		}
		
		return dto;
	}

	@Override
	public UserDTO findByUsername(String username) {
		return toDTO.convert(userRepository.findByUsernameAndEnabledTrue(username));
	}

}
