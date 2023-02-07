package com.restfull.securityController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.restfull.entity.RegistrationEntity;
import com.restfull.repositary.RestReposarity;

@Component
public class CustomeUserDetailService implements UserDetailsService {
	
	@Autowired
	private RestReposarity restrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	RegistrationEntity register =restrepo.findByGmail(username);
	System.out.println(register);	
	if(register == null)
		{
			throw new UsernameNotFoundException("Not found User");
		}
		return new CustomeUserDetails(register);
	}

}
