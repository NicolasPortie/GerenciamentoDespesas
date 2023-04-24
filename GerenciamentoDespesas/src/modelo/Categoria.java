
package modelo;

public class Categoria {
private int id_categoria;
private String nome_categoria;
private Usuario usuario;




public void setId_categoria(int id_categoria) {
    this.id_categoria = id_categoria;
}

public int getId_categoria() {
    return this.id_categoria;
}

public void setNome_categoria(String nome_categoria) {
    this.nome_categoria = nome_categoria;
}

public String getNome_categoria() {
    return this.nome_categoria;
}
public Categoria() {
    this.usuario = new Usuario();
}


public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
}


public Usuario getUsuario() {
    return this.usuario;
}

public void setId_usuario(int id_usuario) {
    this.usuario.setId_usuario(id_usuario);
}

public int getId_usuario() {
    return this.usuario.getId_usuario();
}


@Override
public String toString() {
    return this.id_categoria + " - " + this.nome_categoria;
}

}
