<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.Model.Produto" %>
<%@page import="java.util.ArrayList" %>

<%
    ArrayList<Produto> lista = (ArrayList<Produto>) request.getAttribute("listaProdutos");
    String mensagem = (String) request.getAttribute("mensagem");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="style4.css">
    <title>Listagem de Produtos</title>
</head>
<body>
	
	<header>
    <h1>Lista de Produtos Cadastrados</h1>
    </header>
    <a href="home">Sair</a>

    <% if (mensagem != null && !mensagem.isEmpty()) { %>
        <p><%= mensagem %></p>
    <% } %>

	<nav>
    <form method="post" action="alterar">
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Codigo</th>
                    <th>Nome</th>
                    <th>Categoria</th>
                    <th>Valor</th>
                    <th>Quantidade</th>
                    <th>Ações</th> <!-- Coluna extra para o botão de exclusão -->
                </tr>
            </thead>
            <tbody>
                <% for (int i = 0; i < lista.size(); i++) { %>
                    <tr>
                        <td>
                            <input type="hidden" name="id" value="<%= lista.get(i).getId() %>">
                            <%= lista.get(i).getId() %>
                        </td>
                        <td>
                            <input type="text" name="codigo" value="<%= lista.get(i).getCodigo() %>">
                        </td>
                        <td>
                            <input type="text" name="nome" value="<%= lista.get(i).getNome() %>">
                        </td>
                        <td>
                            <input type="text" name="categoria" value="<%= lista.get(i).getCategoria() %>">
                        </td>
                        <td>
                            <input type="text" name="valor" value="<%= lista.get(i).getValor() %>">
                        </td>
                        <td>
                            <input type="text" name="quantidade" value="<%= lista.get(i).getQuantidade() %>">
                        </td>
                        <td>
                            <button type="submit">Alterar</button>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </form>

   
        
   </nav>
</body>
</html>