package br.com.prova.livraria.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.prova.livraria.dao.UsuarioDao;
import br.com.prova.livraria.modelo.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario();
	private UsuarioDao daoU = new UsuarioDao();
	
	private Integer usuarioId;
	private Integer perfilId;
	
	private List<SelectItem> perfis;

	public void carregarUsuarioPelaId() {
		this.usuario = daoU.buscaPorId(usuarioId);
	}

	public String gravar() {
		System.out.println("Gravando usuario " + this.usuario.getEmail());

		if(this.usuario.getId() == null) {
			daoU.adiciona(this.usuario);
		} else {
			daoU.atualiza(this.usuario);
		}

		this.usuario = new Usuario();

		return "usuario?faces-redirect=true";
	}
	
	public void remover(Usuario usuario) {
		System.out.println("Removendo usuario " + usuario.getEmail());
		daoU.remove(usuario);
		Usuario resultado = daoU.buscaUsuarioPorEmail(usuario);
		if (resultado == null) {
			FacesContext.getCurrentInstance().addMessage("usuario", new FacesMessage("usuario", "Usuário removido com sucesso!"));
		}
	}
	
	public List<Usuario> getUsuarios() {
		return daoU.listaTodos();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	public Integer getPerfilId() {
		return perfilId;
	}
	
	public void setPerfilId(Integer perfilId) {
		this.perfilId = perfilId;
	}
	
	public List<SelectItem> getPerfis() {
		if (perfis == null || perfis.isEmpty()) {
			perfis = new ArrayList<SelectItem>();
			perfis.add(new SelectItem(Integer.valueOf(0), "Administrador"));
			perfis.add(new SelectItem(Integer.valueOf(1), "Usuário"));
		}
		return perfis;
	}
}
