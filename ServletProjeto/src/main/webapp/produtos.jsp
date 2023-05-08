<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="com.Model.Produto" %>
    <%@page import="com.Controller.Servlet" %>
    
    <%
    	@SuppressWarnings("unchecked")
    	ArrayList<Produto> lista = (ArrayList<Produto>) request.getAttribute("listaProdutos");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Listagem de Produtos</title>
</head>
<body>
	<h1>Lista de Produtos Cadastradas</h1>
	<table>
		<thead>
			<tr>
				<th>Codigo</th>
				<th>Nome</th>
				<th>Categoria</th>
				<th>Valor</th>
				<th>Quantiade</th>
			</tr>
		</thead>

		<tbody>
			<%for(int i=0; i<lista.size(); i++){ %>
				<tr>
				<td><%=lista.get(i).getCodigo() %></td>
					<td><%=lista.get(i).getNome() %></td>
					<td><%=lista.get(i).getCategoria() %></td>
					<td><%=lista.get(i).getValor() %></td>
					<td><%=lista.get(i).getQuantidade() %></td>
				</tr>
				<%} %>
		</tbody>
	</table>
	<p>
	<a href="menu">Sair</a>
</body>
</html>