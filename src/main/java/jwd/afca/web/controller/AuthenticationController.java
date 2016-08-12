package jwd.afca.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.afca.service.UserService;
import jwd.afca.web.dto.UserDTO;

	
@RestController
public class AuthenticationController {

	@Autowired
	private UserService userService;

//  @RequestMapping(value = "/user", method = RequestMethod.GET)
//	public ResponseEntity<UserDTO> authenticateUser(@RequestHeader(value = "authorization") String authorization) {
//		UserDTO userDTO = new UserDTO();
//	    if (authorization != null && authorization.startsWith("Basic")) {
//	        // Authorization: Basic base64credentials
//	        String base64Credentials = authorization.substring("Basic".length()).trim();
//	        String credentials = new String(Base64.getDecoder().decode(base64Credentials),
//	                Charset.forName("UTF-8"));
//	        // credentials = username:password
//			String[] values = credentials.split(":", 2);
//			
//			userDTO = userService.authenticateUser(values[0], values[1]); 
//			
//			if (userDTO != null) {
//				//TODO Grant Authorities
//				return new ResponseEntity<>(userDTO, HttpStatus.OK);
//			}
//		}
//		
//	    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//	}
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<UserDTO> loginUser() {
    	UserDTO userDTO = null;
    	
    	if (SecurityContextHolder.getContext().getAuthentication().getName() != null) {
	    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
	    	userDTO = userService.findByUsername(username);	    	
    	}
    	
    	return new ResponseEntity<> (userDTO, HttpStatus.FOUND);
    }    
    
}