package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoJDBC {
	public static void main(String[] args)
							throws SQLException {
		String sgbd = "mysql";
		String ip = "localhost";
		String porta = "3306";
		String bd = "gerenciamentodespesas";
		String usuarioBD = "root";
		String senhaBD = "root";
		
		String stringJDBC =
			"jdbc:"+sgbd+"://"+ip+":"+porta+"/"+bd;
		
		Connection conexao =
				DriverManager.getConnection(
						stringJDBC,
						usuarioBD,
						senhaBD
						);
		
		System.out.println("Conectado!");
		
		conexao.close();
	}
}
