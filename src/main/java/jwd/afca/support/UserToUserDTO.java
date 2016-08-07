package jwd.afca.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.afca.model.User;
import jwd.afca.web.dto.UserDTO;

@Component
public class UserToUserDTO implements Converter<User, UserDTO> {

//	@Autowired
//	private RoleToRoleDTO toRoleDTO;
	
	@Override
	public UserDTO convert(User user) {
		UserDTO dto = new UserDTO();
		
		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setEmail(user.getEmail());
		dto.setTelephoneNumber(user.getTelephoneNumber());
//		dto.setRole(toRoleDTO.convert(user.getRole()));
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
	
}