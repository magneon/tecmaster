package br.com.prova.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.prova.livraria.dao.AutorDao;
import br.com.prova.livraria.dao.LivroDao;
import br.com.prova.livraria.modelo.Autor;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();
	
	private Integer autorId;
	
	private AutorDao daoA = new AutorDao();
	private LivroDao daoL = new LivroDao();

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public void carregarAutorPelaId() {
		this.autor = daoA.buscaPorId(autorId);
	}

	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if(this.autor.getId() == null) {
			daoA.adiciona(this.autor);
		} else {
			daoA.atualiza(this.autor);
		}

		this.autor = new Autor();

		return "autor?faces-redirect=true";
	}
	
	public void remover(Autor autor) {
		System.out.println("Removendo autor " + autor.getNome());
		daoA.remove(autor);
		Autor resultado = daoA.buscaAutorPorEmail(autor);
		if (resultado == null) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("autor", "Autor removido com sucesso!"));
		}
	}
	
	public List<Autor> getAutores() {
		List<Autor> autores = daoA.listaTodos();
		for (Autor autor : autores) {
			autor.setQuantidadeLivros(getQuantidadeLivrosPorAutor(autor));
		}
		return daoA.listaTodos();
	}
	
	private Integer getQuantidadeLivrosPorAutor(Autor autor) {
		return daoL.buscaQuantidadeLivrosPorAutor(autor);
	}
	
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
}
