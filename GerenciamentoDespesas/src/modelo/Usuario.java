package modelo;

public class Usuario {
	private int id_usuario;
	private String nome_usuario;
	private String email;
	private String senha;
	//private Usuario usuario;
	
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	//public void setUsuario(Usuario usuario) {
	  //  this.usuario = usuario;
	//}
	
	public int getId_usuario() {
		return this.id_usuario;
	}
	
	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}
	
	public String getNome_usuario() {
		return this.nome_usuario;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSenha() {
		return this.senha;
	}

	
	@Override
	public String toString() {
	    return "(" + this.id_usuario + " - " + this.nome_usuario + ")"
	            + " (" + this.email + ")";
	}
}
