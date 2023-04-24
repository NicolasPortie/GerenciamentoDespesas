package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;
import bd.ConnectionFactory;
import modelo.Usuario;

public class UsuarioDAO {

	public void inserirUsuario(Usuario usuario) {
	    try {
	        Connection conexao = new ConnectionFactory().getConnection();

	        // Consulta SQL para verificar se ja existe um usuario com o mesmo endere�o de e-mail
	        String verificaSQL = "SELECT COUNT(*) FROM usuario WHERE email = ?";

	        PreparedStatement verificaUsuarioPstmt = conexao.prepareStatement(verificaSQL);
	        verificaUsuarioPstmt.setString(1, usuario.getEmail());

	        ResultSet resultado = verificaUsuarioPstmt.executeQuery();

	        if(resultado.next() && resultado.getInt(1) > 0) {
	            System.out.println("Ja existe um usuario com o mesmo endereco de e-mail.");
	            return;
	        }

	        // Inserção do usuário no banco de dados
	        String sql = "INSERT INTO usuario (id_usuario, nome_usuario, email, senha) VALUES (?, ?, ?, ?)";

	        PreparedStatement pstmt = conexao.prepareStatement(sql);
	        pstmt.setInt(1, usuario.getId_usuario());
	        pstmt.setString(2, usuario.getNome_usuario());
	        pstmt.setString(3, usuario.getEmail());
	        pstmt.setString(4, usuario.getSenha());

	        pstmt.executeUpdate();

	        pstmt.close();
	        conexao.close();
	    } catch(Exception e) {
	        System.out.println(e.getMessage());
	    }
	}


	public void atualizar(Usuario usuario) {
		try {
			Connection conexao = new ConnectionFactory().getConnection();

			String sql = "UPDATE usuario SET"
											+" nome_usuario = ?, "
											+" email = ? "
											+" WHERE id_usuario = ?";

			PreparedStatement pstmt = conexao.prepareStatement(sql);
 
			pstmt.setString(1, usuario.getNome_usuario());
			pstmt.setString(2, usuario.getEmail());
			pstmt.setInt(3, usuario.getId_usuario());

			pstmt.execute();

			pstmt.close();

			conexao.close();

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void excluir (int id_usuario) {
		try {
			Connection conexao = new ConnectionFactory().getConnection();

			String sql = "DELETE FROM usuario WHERE id_usuario = ?";

			PreparedStatement pstmt = conexao.prepareStatement(sql);

			pstmt.setInt(1, id_usuario);

			pstmt.execute();

			pstmt.close();

			conexao.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Usuario pesquisarPorCodigo (int id_usuario) {
		Usuario usuarioRetorno = null;

		try { 
			Connection conexao = new ConnectionFactory().getConnection();

			String sql = "SELECT "
					+ "id_usuario, "
					+ "nome_usuario, "
					+ "email "
					+ "FROM usuario "
					+ "WHERE id_usuario = ?";
			PreparedStatement pstmt = conexao.prepareStatement(sql);

			pstmt.setInt(1, id_usuario);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
			    usuarioRetorno = new Usuario();
			    usuarioRetorno.setId_usuario(id_usuario);
			    usuarioRetorno.setNome_usuario(rs.getString("nome_usuario"));
			    usuarioRetorno.setEmail(rs.getString("email"));
			}

			pstmt.close();
			conexao.close();	

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return usuarioRetorno;
	}

	public List<Usuario> pesquisarTodos(){
		List<Usuario> usuarios = new ArrayList<>(); // declarar a lista de usuarios

		try {
			Connection conexao = new ConnectionFactory().getConnection();

			String sql = "SELECT "
						+"id_usuario, "
						+"nome_usuario, "
						+"email "
						+"FROM usuario";

			PreparedStatement pstmt = conexao.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				Usuario usuario = new Usuario(); // renomear a variável dentro do loop
				usuario.setId_usuario(rs.getInt(1));
				usuario.setNome_usuario(rs.getString(2));
				usuario.setEmail(rs.getString(3));
				
				usuarios.add(usuario);
			}
				
			pstmt.close();
			
			conexao.close();
			}catch(Exception e ) {
				System.out.println(e.getMessage());
			}
			return usuarios; // retornar a lista de usuarios ao invés da variável usuario
				
	}




}