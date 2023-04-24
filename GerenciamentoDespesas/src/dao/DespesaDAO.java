package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bd.ConnectionFactory;
import modelo.Categoria;
import modelo.Despesa;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DespesaDAO {
public void inserirDespesa(Despesa despesa) {
    try {
        Connection conexao = new ConnectionFactory().getConnection();

        String sql = "INSERT INTO despesa (data_despesa, descricao, valor, id_categoria, id_usuario) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement pstmt = conexao.prepareStatement(sql);

        pstmt.setDate(1, java.sql.Date.valueOf(despesa.getData_despesa()));
        pstmt.setString(2, despesa.getDescricao());
        pstmt.setDouble(3, despesa.getValor());
        pstmt.setInt(4, despesa.getCategoria().getId_categoria());
        pstmt.setInt(5, despesa.getUsuario().getId_usuario());

        pstmt.execute();

        pstmt.close();

        conexao.close();
    } catch(SQLException e) {
        System.out.println(e.getMessage());
    }
}


public void atualizar(Despesa despesa) {
    try {
        Connection conexao =
                new ConnectionFactory().getConnection();

        String sql = "UPDATE despesa SET " +
                                "data_despesa = ?, " +
                                "descricao = ?, " +
                                "valor = ?, " +
                                "id_categoria = ? " +
                            "WHERE id_despesa = ? AND id_usuario = ?";

        PreparedStatement pstmt = 
                conexao.prepareStatement(sql);

     
        pstmt.setDate(1, java.sql.Date.valueOf(despesa.getData_despesa()));
        pstmt.setString(2, despesa.getDescricao());
        pstmt.setDouble(3, despesa.getValor());
        pstmt.setInt(4, despesa.getCategoria().getId_categoria());
        pstmt.setInt(5, despesa.getId_despesa());
        pstmt.setInt(6, despesa.getUsuario().getId_usuario());

        pstmt.execute();

        pstmt.close();

        conexao.close();
    } catch(SQLException e) {
        System.out.println(e.getMessage());
    }
}


public void excluir(int id_despesa) {
	try {
		Connection conexao =
				new ConnectionFactory().getConnection();
		
		String sql = "DELETE FROM despesa WHERE id_despesa = ? AND id_usuario = ? ";
		
		PreparedStatement pstmt =
				conexao.prepareStatement(sql);
		
		pstmt.setInt(1, id_despesa);
	

		pstmt.execute();
		
		pstmt.close();
		
		conexao.close();
	} catch(SQLException e) {
		System.out.println(e.getMessage());
	}
}

public Despesa pesquisarPorCodigoDespesa(int id_despesa, int id_usuario) {
    Despesa despesaRetorno = null;

    try {
        Connection conexao = new ConnectionFactory().getConnection();

        String sql = "SELECT " +    
                        "data_despesa, " +
                        "descricao, " +
                        "valor, " +
                        "id_categoria " +
                    "FROM despesa " + // adicione um espaço em branco aqui
                    "WHERE id_despesa = ? AND id_usuario = ?";


        PreparedStatement pstmt = conexao.prepareStatement(sql);

        pstmt.setInt(1, id_despesa);
        pstmt.setInt(2, id_usuario); 

        ResultSet rs = pstmt.executeQuery();


        if(rs.next()) {
            DespesaDAO despesaDAO = new DespesaDAO();
            Despesa despesa = despesaDAO.pesquisarPorCodigoDespesa(rs.getInt(1), id_usuario); // adicione o segundo argumento aqui

            despesaRetorno = new Despesa();
            despesaRetorno.setId_despesa(id_despesa);

            despesaRetorno.setData_despesa(rs.getDate(1).toLocalDate());
            despesaRetorno.setDescricao(rs.getString(2));
            despesaRetorno.setValor(rs.getDouble(3));
            despesaRetorno.setCategoria(despesa.getCategoria());
        }

        pstmt.close();
        conexao.close();
    } catch(SQLException e) {
        System.out.println(e.getMessage());
    }

    return despesaRetorno;
}

   
        
        public List<Despesa> pesquisarPorCategoria(int id_categoria) {
        	List<Despesa> despesas = new ArrayList<>();
        	int id_usuario = 1; // exemplo de atribuição de valor à variável id_usuario
        	try {
        	    Connection conexao = new ConnectionFactory().getConnection();

        	    String sql = "SELECT " +
        	            "data_despesa, " +
        	            "descricao, " +
        	            "valor, " +
        	            "id_categoria " +
        	            "FROM despesa " + 
        	            "WHERE id_categoria = ? AND id_usuario = ?";

        	    PreparedStatement pstmt = conexao.prepareStatement(sql);
        	    pstmt.setInt(1, id_categoria); 
        	    pstmt.setInt(2, id_usuario); 

        	    ResultSet rs = pstmt.executeQuery();

        	    while (rs.next()) {
        	        DespesaDAO despesaDAO = new DespesaDAO();
        	        Despesa despesa = despesaDAO.pesquisarPorCodigoDespesa(rs.getInt(5), id_usuario);
        	        despesa.setData_despesa(LocalDate.parse(rs.getString(1), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        	        despesa.setDescricao(rs.getString(2));
        	        despesa.setValor(rs.getFloat(3));
        	        CategoriaDAO categoriaDAO = new CategoriaDAO();
        	        Categoria categoria = categoriaDAO.pesquisarPorCodigo(rs.getInt(4));
        	        despesa.setCategoria(categoria);

        	        despesas.add(despesa);
        	    }

        	    pstmt.close();
        	    conexao.close();
        	} catch (SQLException e) {
        	    System.out.println(e.getMessage());
        	}

        	return despesas;
        }
        public List<Despesa> pesquisarPorData(LocalDate dataInicial, LocalDate dataFinal) {
        	List<Despesa> despesas = new ArrayList<>();
        	int id_usuario = 1; // exemplo de atribuição de valor à variável id_usuario
        	try {
        	    Connection conexao = new ConnectionFactory().getConnection();

        	    String sql = "SELECT " +
        	            "data_despesa, " +
        	            "descricao, " +
        	            "valor, " +
        	            "id_categoria " +
        	            "FROM despesa " + 
        	            "WHERE data_despesa BETWEEN ? AND ? AND id_usuario = ?";

        	    PreparedStatement pstmt = conexao.prepareStatement(sql);
        	    pstmt.setDate(1, java.sql.Date.valueOf(dataInicial));
        	    pstmt.setDate(2, java.sql.Date.valueOf(dataFinal));
        	    pstmt.setInt(3, id_usuario);

        	    ResultSet rs = pstmt.executeQuery();

        	    while (rs.next()) {
        	        DespesaDAO despesaDAO = new DespesaDAO();
        	        Despesa despesa = despesaDAO.pesquisarPorCodigoDespesa(rs.getInt(5), id_usuario);
        	        despesa.setData_despesa(LocalDate.parse(rs.getString(1), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        	        despesa.setDescricao(rs.getString(2));
        	        despesa.setValor(rs.getFloat(3));
        	        CategoriaDAO categoriaDAO = new CategoriaDAO();
        	        Categoria categoria = categoriaDAO.pesquisarPorCodigo(rs.getInt(4));
        	        despesa.setCategoria(categoria);

        	        despesas.add(despesa);
        	    }

        	    pstmt.close();
        	    conexao.close();
        	} catch (SQLException e) {
        	    System.out.println(e.getMessage());
        	}

        	return despesas;
        }
    }
