package com.example.clinica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserPrincipal implements UserDetails {
	
	private Usuarios usuario;
	private List<String> roles;

	public MyUserPrincipal(Usuarios usuario, List<String> roles) {
		this.usuario = usuario;
		this.roles = roles;
	}

	@Override
	public String getPassword() {
		return usuario.getPassword();
	}

	@Override
	public String getUsername() {
		return usuario.getNombre();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> lst = new ArrayList<GrantedAuthority>();
		for (String rol : roles) {
			lst.add(new SimpleGrantedAuthority(rol));
		}
		return lst;
	}
}
