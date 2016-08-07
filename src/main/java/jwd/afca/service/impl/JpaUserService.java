package jwd.afca.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jwd.afca.model.User;
import jwd.afca.repository.UserRepository;
import jwd.afca.service.UserService;

@Service
public class JpaUserService implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public Page<User> findAll(int page, int itemsPerPage, Sort.Direction direction, String property) {
		return userRepository.findAll(new PageRequest(page, itemsPerPage, direction, property));
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.delete(id);
	}

	@Override
	public Page<User> findByUsernameContains(int page, int itemsPerPage, String search) {
		return userRepository.findByUsernameContains(new PageRequest(page, itemsPerPage), search);
	}

}
