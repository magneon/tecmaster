package br.com.prova.livraria.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.prova.livraria.modelo.Autor;

public class AutorDao {

	public static ArrayList<Autor> listaAutor = new ArrayList<Autor>();

	public void pesist(Autor autor) {
		Autor resultado = buscaAutorPorEmail(autor);
		if (resultado == null) {
			listaAutor.add(autor);
		}
	}

	public Autor buscaAutorPorEmail(Autor outroAutor) {
		for (Autor autor : listaAutor) {
			if (autor.getEmail().equalsIgnoreCase(outroAutor.getEmail())) {
				return autor;
			}
		}
		return null;
	}

	public void drop() {
		// TODO Auto-generated method stub
		listaAutor.clear();
	}

	public List<Autor> listaTodos() {
		// TODO Auto-generated method stub
		return listaAutor;
	}

	public Autor buscaPorId(Integer autorId) {

		// TODO Auto-generated method stub
		for (Autor autor : listaAutor) {
			if (autorId == autor.getId()) {
				return autor;
			}
		}

		return null;

	}

	public void remove(Autor autor) {
		listaAutor.remove(autor);
	}

	public void adiciona(Autor autor) {
		autor.setId(listaAutor.size() + 1);
		pesist(autor);
	}

	public void atualiza(Autor autor) {
		// TODO Auto-generated method stub
		
	}
}
