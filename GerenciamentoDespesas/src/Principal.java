

import modelo.Usuario;
import modelo.Despesa;
import modelo.Categoria;
import dao.UsuarioDAO;
import dao.DespesaDAO;
import dao.CategoriaDAO;
import java.time.LocalDate;



public class Principal {
	public static void main(String[] args) {
		
		//DEVE INSERIR PRIMEIRO O USUARIO DEPOIS INSERIR AS OUTRAS INFORMAÇÕES
		
		 // Criando o Usuario
		Usuario exemploUsuario3 = new Usuario();
		exemploUsuario3.setId_usuario(3);
		exemploUsuario3.setNome_usuario("Exemplo3");
		exemploUsuario3.setEmail("exemplo3@exemplo.com");
		exemploUsuario3.setSenha("senhaExemplo3");
		
		// Inserindo o usuario no banco de dados
		UsuarioDAO funcoesUsuario = new UsuarioDAO();
		funcoesUsuario.inserirUsuario(exemploUsuario3);
	
/*
		// Criando a categoria
		Categoria exemploCategoria4 = new Categoria();
		exemploCategoria4.setId_categoria(4);
		exemploCategoria4.setNome_categoria("Roupas");
		exemploCategoria4.setUsuario(exemploUsuario4);

		// Inserindo a categoria no banco de dados
		CategoriaDAO funcoesCategoria = new CategoriaDAO();
		funcoesCategoria.inserirCategoria(exemploCategoria4);
		int id_categoria = exemploCategoria4.getId_categoria();
		
		//Criando a despesa
		Despesa exemploDespesa4 = new Despesa();
		exemploDespesa4.setId_despesa(2);
		exemploDespesa4.setData_despesa(LocalDate.of(2023, 3, 15));
		exemploDespesa4.setDescricao("Compras no Mercado");
		exemploDespesa4.setValor(120.00);
		exemploDespesa4.setCategoria(exemploCategoria4);
		exemploDespesa4.setId_categoria(exemploCategoria4.getId_categoria());
		exemploDespesa4.setUsuario(exemploUsuario4);
		exemploDespesa4.setId_usuario(exemploUsuario4.getId_usuario());

		// Inserindo a despesa no banco de dados
		DespesaDAO funcoesDespesa = new DespesaDAO();
		funcoesDespesa.inserirDespesa(exemploDespesa4);
		int id_despesa = exemploDespesa4.getId_despesa(); 

		
		//System.out.println(funcoesUsuario);
		System.out.println(funcoesUsuario.pesquisarPorCodigo(2)); */
		

		
	}

		

		
}

