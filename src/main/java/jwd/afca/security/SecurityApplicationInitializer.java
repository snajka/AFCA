package jwd.afca.security;

import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityApplicationInitializer 
		extends AbstractSecurityWebApplicationInitializer {
	
	public SecurityApplicationInitializer() {
		super(SecurityConfig.class);
	}

}
