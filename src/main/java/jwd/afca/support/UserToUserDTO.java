package jwd.afca.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import jwd.afca.model.User;
import jwd.afca.web.dto.UserDTO;

@Component
public class UserToUserDTO implements Converter<User, UserDTO> {

	@Override
	public UserDTO convert(User user) {
		UserDTO dto = new UserDTO();
		
		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setEmail(user.getEmail());
		dto.setTelephoneNumber(user.getTelephoneNumber());
		dto.setRole(user.getRole());
//		dto.setAds(user.getAds());
		
		return dto;
	}
	
	public List<UserDTO> convert(List<User> users){
		List<UserDTO> ret = new ArrayList<>();
		
		for(User user : users){
			ret.add(convert(user));
		}
		
		return ret;
	}
	
	public List<UserDTO> convert(Page<User> usersPage){
		List<User> users = usersPage.getContent();
		List<UserDTO> dtos = convert(users);
		
		return dtos;
	}
	
}