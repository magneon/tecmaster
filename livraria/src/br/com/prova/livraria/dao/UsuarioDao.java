package br.com.prova.livraria.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.prova.livraria.modelo.Usuario;

public class UsuarioDao {
	
	public static ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();

	public Usuario existe(Usuario usuario) {
		
		for (Usuario u : listaUsuario) {
			if(usuario.getEmail().equals(u.getEmail()) && usuario.getSenha().equals(u.getSenha()) ){
				return u;			
			}
		}
		
		return null;

	}
	
	
	public void pesist(Usuario usuario){
		Usuario resultado = buscaUsuarioPorEmail(usuario);
		if (resultado == null) {
			listaUsuario.add(usuario);
		}
	}

	public void drop() {
		listaUsuario.clear();
	}


	public Usuario buscaPorId(Integer usuarioId) {
		for (Usuario usuario : listaUsuario) {
			if (usuario.getId().equals(usuarioId)) {
				return usuario;
			}
		}
		return null;
	}


	public void adiciona(Usuario usuario) {
		usuario.setId(listaUsuario.size() + 1);
		pesist(usuario);
	}


	public void atualiza(Usuario usuario) {
		
	}


	public void remove(Usuario usuario) {
		listaUsuario.remove(usuario);
	}


	public Usuario buscaUsuarioPorEmail(Usuario usuario) {
		for (Usuario outro : listaUsuario) {
			if (usuario.getEmail().equalsIgnoreCase(outro.getEmail())) {
				return outro;
			}
		} 
		return null;
	}


	public List<Usuario> listaTodos() {
		return listaUsuario;
	}
}
