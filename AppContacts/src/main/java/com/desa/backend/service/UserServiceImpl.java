package com.desa.backend.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.desa.backend.entity.UserRole;
import com.desa.backend.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserDetailsService {
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.desa.backend.entity.User user = userRepository.findByUsername(username);
		List<GrantedAuthority> authorities = buildAuthorities(user.getUserRole());
		return buildUser(user, authorities);
	}
	
	/**
	 * 
	 * @param user Entidad user
	 * @param authorities Listado de roles del objeto GrantedAuthority de spring security
	 * @return objeto usuario de spring security
	 */
	private User buildUser(com.desa.backend.entity.User user, List<GrantedAuthority> authorities) {
		
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), 
				true, true, true, authorities);
	}
	
	/**
	 * Transforma nuestro set de roles a un listado de roles del objeto GrantedAuthority de spring security
	 * @param userRole
	 * @return listado de roles del objeto GrantedAuthority de spring security
	 */
	private List<GrantedAuthority> buildAuthorities(Set<UserRole> userRoles) {
		
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		
		for(UserRole userRole : userRoles) {
			auths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		
		return new ArrayList<GrantedAuthority>(auths);
	}

}
