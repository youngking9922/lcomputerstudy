package com.SpringTest2.example.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	@Override
	   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	      return null;
	   }

}
