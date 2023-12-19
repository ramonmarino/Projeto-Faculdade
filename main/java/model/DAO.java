package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	/** Módulo de conexão **/
	// Parâmetro de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbcontas?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "1234";

	// Método de conexão

	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		// teste de conexão
	}

	public void testeConexao() {
		try {
			Connection con = conectar();
			System.out.println(con);
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/** CRUD CREATE **/
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome,fone,email) value (?,?,?)";

		try {
			// abrir a conexão com o banco
			Connection con = conectar();
			// Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parâmetros (?)
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());

			// Executar a query
			pst.executeUpdate();
			// Encerrar a conexão com o banco
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// CRUD READ
	// método responsável pela listagem de todos os contatos no banco de dados.
	public ArrayList<JavaBeans> listarContatos() {
		// quando trabalhar com banco sempre usar try catch abrir conexão e encerrar
		// Criando um objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = " select * from contatos order by nome";
		try {
			// Abrindo conexão
			Connection con = conectar();
			// Prepara a area de "Espera" das informações para só então executar.
			PreparedStatement pst = con.prepareStatement(read);
			// ResultSet retorna para você umas tabela quando executado.
			// usado para armazenar o retorno do banco de dados temporariamente em um
			// objeto.
			ResultSet rs = pst.executeQuery();
			// O laço abaixo será executado enquanto houver contatos
			while (rs.next()) {
				// Variáveis de apoio que recebe os dados do banco.
				String idcon = rs.getString(1); // recebe o primeiro campo do banco de dados.
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				// armazenando e populando a lista de ArrayList.
				contatos.add(new JavaBeans(idcon, nome, fone, email));

			}
			// fechar conexão do banco.
			con.close();
			return contatos;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	/* CRUD UPDATE */
	// selecionar o contato
	public void selecionarContato(JavaBeans contato) {
		String read2 = "select * from contatos where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, contato.getIdcon());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Editar o Contato
	public void alterarContato(JavaBeans contato) {
		String create = "update contatos set nome=?,fone=?,email=? where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdcon());
			pst.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Crud Delete
	public void deletarContato(JavaBeans contato) {
		String delete = "delete from contatos where idcon=?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, contato.getIdcon());
			pst.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
