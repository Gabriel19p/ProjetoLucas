package com.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		String create = "insert into produto (codigo, nome, categoria, valor, quantidade) values (?, ?,?,?,?)";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection(db_url, db_user, db_password);
			System.out.println("Conectado ao BD");
			PreparedStatement pst = c.prepareStatement(create);
			pst.setInt(1, p.getCodigo());
			pst.setString(2, p.getNome());
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
			PreparedStatement ps = c.prepareStatement("SELECT * FROM produto");
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				pAux.setId(resultSet.getInt("id"));
				pAux.setCodigo(resultSet.getInt("codigo"));
				pAux.setNome(resultSet.getString("nome"));
				pAux.setCategoria(resultSet.getString("categoria"));
				pAux.setValor(resultSet.getFloat("valor"));
				pAux.setQuantidade(resultSet.getInt("quantidade"));
				listaProdutos.add(pAux);
				pAux = new Produto();
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Falha ao conectar ao MySql" + e.getMessage());
		}
		return listaProdutos;
	}
	
	public void excluirProduto(int id) {
	    String delete = "DELETE FROM produto WHERE id = ?";
	    
	    try (Connection c = DriverManager.getConnection(db_url, db_user, db_password);
	        PreparedStatement pst = c.prepareStatement(delete)) {
	        pst.setInt(1, id);
	        pst.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println("Falha ao excluir o produto do banco de dados: " + e.getMessage());
	    }
	}
	
	public void atualizarProduto(Produto p) {
	    String update = "UPDATE produto SET nome = ?, categoria = ?, valor = ?, quantidade = ? WHERE id = ?";

	    try (Connection connection = DriverManager.getConnection(db_url, db_user, db_password);
	         PreparedStatement statement = connection.prepareStatement(update)) {
	        statement.setString(1, p.getNome());
	        statement.setString(2, p.getCategoria());
	        statement.setFloat(3, p.getValor());
	        statement.setInt(4, p.getQuantidade());
	        statement.setInt(5, (int) p.getId());
	        statement.executeUpdate();
	        System.out.println("Produto atualizado com sucesso!");
	    } catch (SQLException e) {
	        System.out.println("Erro ao atualizar o produto: " + e.getMessage());
	    }
	}
	
	public Produto consultarProdutoPorId(int id) {
	    Produto produto = null;
	    String query = "SELECT * FROM produto WHERE id = ?";
	    
	    try (Connection connection = DriverManager.getConnection(db_url, db_user, db_password);
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setInt(1, id);
	        ResultSet resultSet = statement.executeQuery();
	        
	        if (resultSet.next()) {
	            produto = new Produto();
	            produto.setId(resultSet.getInt("id"));
	            produto.setCodigo(resultSet.getInt("codigo"));
	            produto.setNome(resultSet.getString("nome"));
	            produto.setCategoria(resultSet.getString("categoria"));
	            produto.setValor(resultSet.getFloat("valor"));
	            produto.setQuantidade(resultSet.getInt("quantidade"));
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro ao consultar o produto por ID: " + e.getMessage());
	    }
	    
	    return produto;
	}
}