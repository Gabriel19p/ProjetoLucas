package com.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.Model.Produto;

public class Repository {
	private String db_url;
	private String db_user;
	private String db_password;

	public Repository(String db_url, String db_user, String db_password) {
		this.db_url = db_url;
		this.db_user = db_user;
		this.db_password = db_password;
	}
	
	public void cadastrarProduto(Produto p) {
		 String create = "insert into produto (id, código, nome, categoria, valor, quantidade) values (?,?,?,?,?,?)";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection(db_url, db_user, db_password);
			System.out.println("Conectado ao BD");
			PreparedStatement pst = c.prepareStatement(create);
			pst.setInt(1, p.getId());
			pst.setInt(2, p.getCodigo());
			pst.setString(3, p.getCategoria());
			pst.setFloat(4, p.getValor());
			pst.setInt(5, p.getQuantidade());
		
			// executar a query
			pst.executeUpdate();
			// Encerrar conexão
			c.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public List<Produto> consultarProdutos() {
		Produto pAux = new Produto();
		List<Produto> listaProdutos = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection(db_url, db_user, db_password);
			System.out.println("Conectado ao BD");
			PreparedStatement ps = c.prepareStatement("SELECT * FROM produtos");
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				pAux.setCodigo(resultSet.getString("codigo"));
				pAux.setNome(resultSet.getString("nome"));
				pAux.setCategoria(resultSet.getString("categoria"));
				pAux.setValor(resultSet.getString("valor"));
				pAux.setQuantidade(resultSet.getString("quantidade"));
				listaProdutos.add(pAux);
				pAux = new produto();
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Falha ao conectar ao MySql" + e.getMessage());
		}
		return listaProdutos;
	}

}
