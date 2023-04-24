package modelo;

import java.time.LocalDate;

public class Despesa {
    private int id_despesa;
    private LocalDate data_despesa;
    private String descricao;
    private double valor;
    private Categoria categoria;
    private Usuario usuario;

    public void setId_despesa(int id_despesa) {
        this.id_despesa = id_despesa;
    }

    public int getId_despesa() {
        return this.id_despesa;
    }

    public void setData_despesa(LocalDate data_despesa) {
        this.data_despesa = data_despesa;
    }

    public LocalDate getData_despesa() {
        return this.data_despesa;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return this.valor;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }
    
    public void setId_categoria(int id_categoria) {
        this.categoria.setId_categoria(id_categoria);
    }

    public int getId_categoria() {
        return this.categoria.getId_categoria();
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
        return this.id_despesa + " - " + this.data_despesa
                + " - " + this.descricao + " - " + this.valor
                + " - " + this.categoria;
    }
}
