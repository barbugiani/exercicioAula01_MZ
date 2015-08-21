/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp.senac.exercicioaula01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author Marcelo
 */
public class PessoaDAO {
	
	private static Connection conexao;
	private static Statement comando;
	
	public static void main(String[] args) {		
		
		Vector<Pessoa> listaPessoas = listarTodos();
		if (listaPessoas != null) {			

			for (Iterator<Pessoa> iterator = listaPessoas.iterator(); iterator.hasNext();) {
				Pessoa pessoa = iterator.next();
				System.out.println("Pessoa " + pessoa.getNome());
			}
			
		}
		
	}	
	
	public static Vector<Pessoa> listarTodos() {
		iniciarConexao();
		Vector<Pessoa> vetorPessoas = new Vector<Pessoa>();
		ResultSet resultadoLista;	
		
		try {			
			resultadoLista = comando.executeQuery("SELECT * FROM Pessoa ");
			while (resultadoLista.next()) {
				
				Pessoa pessoaTemp = new Pessoa();
				// Setar TODOS os atributos
				pessoaTemp.setNome(resultadoLista.getString("Nome"));
				pessoaTemp.setDataNascimento(resultadoLista.getString("Data de Nascimento"));
				pessoaTemp.setEmail(resultadoLista.getString("Email"));
				pessoaTemp.setTelefone(resultadoLista.getString("Telefone"));
				
				vetorPessoas.add(pessoaTemp);
			}
			
			return vetorPessoas;
			
		} catch( SQLException sqlError) {
			System.out.println("======= Falha ao conectar no banco ");
			System.out.println("Mensagem de Erro " + sqlError.getMessage());
			System.out.println("C�digo do Erro " + sqlError.getErrorCode());				
			
			return null;
		} finally {
			encerrarConexao();
		}
	}
	
	public static void iniciarConexao() {
		try {
			conexao = Conexao.getConexaoBancoDados();
			comando = conexao.createStatement();
			System.out.print("Conex�o realizada com sucesso");
		} catch( SQLException sqlError) {
			System.out.println("======= Falha ao conectar no banco ");
			System.out.println("Mensagem de Erro " + sqlError.getMessage());
			System.out.println("Código do Erro " + sqlError.getErrorCode());		
		}
	}
	
	public static void encerrarConexao() {
		try {
			conexao = Conexao.getConexaoBancoDados();
			comando = conexao.createStatement();
			System.out.print("Conexão realizada com sucesso");
		} catch( SQLException sqlError) {
			System.out.println("======= Falha ao conectar no banco ");
			System.out.println("Mensagem de Erro " + sqlError.getMessage());
			System.out.println("Código do Erro " + sqlError.getErrorCode());		
		}
	}	
	
}