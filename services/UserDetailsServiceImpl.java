package com.projet.BackendPfe.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projet.BackendPfe.model.User;
import com.projet.BackendPfe.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}
	public String sendReset() {
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
	          +"lmnopqrstuvwxyz!@#$%&";
			Random rnd = new Random();
			StringBuilder sb = new StringBuilder(10);
			for (int i = 0; i < 10; i++)
				sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return (sb.toString());
	}


}