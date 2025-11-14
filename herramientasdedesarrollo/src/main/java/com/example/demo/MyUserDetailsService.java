package com.example.clinica;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UsuariosRepository usuariosRepository;
	@Autowired
	private RolesRepository rolesRepository;
	@Autowired
	private UsuarioRolesRepository urRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuarios usuario = usuariosRepository.findByNombre(username);
		if (usuario == null) {
			throw new UsernameNotFoundException(username);
		}
		usuario.setPassword("{noop}" + usuario.getPassword());
		List<UsuariosRoles> lst = urRepository.findByIdUsuario(usuario.getId());
		List<String> roles = new ArrayList<String>();
		System.out.println("usuario: " + usuario.getNombre());
		for (UsuariosRoles ur : lst) {
			Roles rol = rolesRepository.findById(ur.getIdRol()).get();
			roles.add(rol.getNombre());
			System.out.println("rol: " + rol.getNombre());
		}
		return new MyUserPrincipal(usuario, roles);
	}
}
