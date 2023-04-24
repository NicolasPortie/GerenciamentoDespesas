package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bd.ConnectionFactory;

import modelo.Categoria;
import modelo.Usuario;

public class CategoriaDAO {
	public void inserirCategoria(Categoria categoria) {
		try {
			Connection conexao = new ConnectionFactory().getConnection();
			
			// Inserção da categoria no banco de dados
			String insereCategoriaSQL = "INSERT INTO categoria (nome_categoria, id_usuario) VALUES (?, ?)";

			PreparedStatement insereCategoriaPstmt = conexao.prepareStatement(insereCategoriaSQL);
			insereCategoriaPstmt.setString(1, categoria.getNome_categoria());
			insereCategoriaPstmt.setInt(2, categoria.getUsuario().getId_usuario());
			insereCategoriaPstmt.executeUpdate();

			insereCategoriaPstmt.close();
			conexao.close();
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
    public void excluir(int id_categoria) {
        try {
            Connection conexao = new ConnectionFactory().getConnection();

            String sql = "DELETE FROM categoria WHERE id_categoria = ?";

            PreparedStatement pstmt = conexao.prepareStatement(sql);

            pstmt.setInt(1, id_categoria);

            pstmt.execute();

            pstmt.close();

            conexao.close();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
    public List<Categoria> pesquisarTodasCategorias() {
        List<Categoria> categorias = new ArrayList<>();

        try {
            Connection conexao = new ConnectionFactory().getConnection();

            String sql = "SELECT id_categoria, nome_categoria, id_usuario FROM categoria";
            // adicionado o ID da categoria na consulta

            PreparedStatement pstmt = conexao.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario = usuarioDAO.pesquisarPorCodigo(rs.getInt(3));

                Categoria categoria = new Categoria();
                categoria.setId_categoria(rs.getInt(1));
                categoria.setNome_categoria(rs.getString(2));
                categoria.setUsuario(usuario);

                categorias.add(categoria);
            }

            pstmt.close();

            conexao.close();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return categorias;
    }

    public Categoria pesquisarPorCodigo(int id_categoria) {
        Categoria categoria = null;

        try {
            Connection conexao = new ConnectionFactory().getConnection();

            String sql = "SELECT nome_categoria, id_usuario FROM categoria WHERE id_categoria = ?";

            PreparedStatement pstmt = conexao.prepareStatement(sql);

            pstmt.setInt(1, id_categoria);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario = usuarioDAO.pesquisarPorCodigo(rs.getInt(2));

                categoria = new Categoria();
                categoria.setId_categoria(id_categoria);
                categoria.setNome_categoria(rs.getString(1));
                categoria.setUsuario(usuario);
            }

            pstmt.close();

            conexao.close();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return categoria;
    }
}
