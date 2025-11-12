package com.example.clinica;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRolesRepository extends JpaRepository<UsuariosRoles, Integer> {

	public List<UsuariosRoles> findByIdUsuario(int idUsuario);
}
