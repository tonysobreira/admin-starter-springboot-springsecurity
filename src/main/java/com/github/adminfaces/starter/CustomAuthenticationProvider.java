package com.github.adminfaces.starter;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.github.adminfaces.starter.facade.Facade;
import com.github.adminfaces.starter.model.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by aLeXcBa1990 on 24/11/2018.
 * 
 */

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private Facade facade;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String user = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		// Here you can add a database connection for your custom login
		
		User userDB = facade.findUserByEmail(user);
		
		if (userDB != null) {
			if (userDB.isEnabled()) {
				Collection<? extends GrantedAuthority> authorities = userDB.getAuthorities();
				return new UsernamePasswordAuthenticationToken(user, password, authorities);
			} else {
				throw new BadCredentialsException("This user is disabled.");
			}
		} else {
			throw new UsernameNotFoundException("Login and/or Password invalid(s).");
		}
        
        
		//TEST
//		List<GrantedAuthority> grantedAuths = new ArrayList<>();
//		
//		if (user.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")){
//			grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//		} else if (user.equalsIgnoreCase("user") && password.equalsIgnoreCase("user")){
//			grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
//		}
//		
//		if (grantedAuths.size() > 0) {
//			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, password, grantedAuths);
//			return auth;
//		}
			
		//return null;
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {

		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));

	}
	
}
