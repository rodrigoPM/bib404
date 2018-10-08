package com.bib404.system_bib404.service.impl;

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

import com.bib404.system_bib404.Repository.UsuarioRepository;
import com.bib404.system_bib404.model.Usuario;

@Service("userService")
public class UserService implements UserDetailsService{
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usImpl;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usImpl.findBy(username);
		ArrayList<String> roles = new ArrayList<String>();
		roles.add(user.getRol());
		List<GrantedAuthority> auts = buildAuthorities(roles);
		return buildUser(user, auts);
	}
	
	private User buildUser(Usuario us, List<GrantedAuthority> auts) {
		return new User(us.getUsername(), us.getPassword(), true, true, true, true, auts);
	}
	
	private List<GrantedAuthority> buildAuthorities(ArrayList<String> roles){
		Set<GrantedAuthority> auts = new HashSet<GrantedAuthority>();
		for(String r : roles) {
			auts.add(new SimpleGrantedAuthority(r));
		}
		return new ArrayList<GrantedAuthority>(auts);
	}
	
	
}
