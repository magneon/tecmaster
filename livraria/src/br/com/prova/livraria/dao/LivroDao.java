package br.com.prova.livraria.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.prova.livraria.modelo.Autor;
import br.com.prova.livraria.modelo.Livro;

public class LivroDao {

	public static ArrayList<Livro> listaLivro = new ArrayList<Livro>();
	
	
	public void pesist(Livro livro){
		Livro resultado = buscaLivroPorISBN(livro);
		if (resultado == null) {
			listaLivro.add(livro);
		}
	}


	public Livro buscaLivroPorISBN(Livro livro) {
		for (Livro outroLivro : listaLivro) {
			if (outroLivro.getIsbn().equalsIgnoreCase(livro.getIsbn())) {
				return livro;
			}
		}
		return null;
	}


	public void drop() {
		// TODO Auto-generated method stub
		listaLivro.clear();
	}


	public List<Livro> listaTodos() {
		// TODO Auto-generated method stub
		return listaLivro;
	}


	public Livro buscaPorId(Integer id) {
		// TODO Auto-generated method stub
		for (Livro livro : listaLivro) {
			if(id == livro.getId()){
				return livro;
							
			}
		}
		
		return null;
	}


	public void adiciona(Livro livro) {
		listaLivro.add(livro);
		
	}


	public void atualiza(Livro livro) {
		for (Livro l : listaLivro) {
			if(livro.getId() == l.getId()){
				
				l.setTitulo(livro.getTitulo());			
			}
		}
	}


	public void remove(Livro livro) {
		listaLivro.remove(livro);
	}


	public Integer buscaQuantidadeLivrosPorAutor(Autor autor) {
		Integer total = Integer.valueOf(0);
	
		for (Livro livro : listaLivro) {
			List<Autor> autores = livro.getAutores();
			for (Autor outroAutor : autores) {
				if (autor.getNome().equalsIgnoreCase(outroAutor.getNome())) {
					total += 1;
				}
			}
		}
		
		return total;
	}
}
