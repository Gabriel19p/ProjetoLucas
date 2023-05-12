package com.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.Model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Repository.Repository;

@WebServlet(urlPatterns = { "/inserir", "/listar", "/home", "/add", "/deletar", "/alterar" })
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Repository r = new Repository("jdbc:mysql://localhost:3306/projeto1", "root", "");

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();
		if (method.equals("POST")) {
			System.out.println("POST");
			doPost(request, response);
		} else if (method.equals("GET")) {
			System.out.println("GET");
			doGet(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/home")) {
			response.sendRedirect("index.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();

		if (path.equals("/add")) {
			response.sendRedirect("cadastrar.html");
		}

		if (path.equals("/inserir")) {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			String nome = request.getParameter("nome");
			String categoria = request.getParameter("categoria");
			float valor = Float.parseFloat(request.getParameter("valor"));
			int quantidade = Integer.parseInt(request.getParameter("quantidade"));
			Produto p = new Produto(codigo,nome,categoria, valor, quantidade);
			r.cadastrarProduto(p);
			request.setAttribute("listaProdutos", listarProdutos());
			RequestDispatcher rd = request.getRequestDispatcher("listar.jsp");
			rd.forward(request, response);
		}
		if (path.equals("/listar")) {
			request.setAttribute("listaProdutos", listarProdutos());
			RequestDispatcher rd = request.getRequestDispatcher("listar.jsp");
			rd.forward(request, response);
		}
		
		if (path.equals("/deletar")) {
			request.setAttribute("listaProdutos", listarProdutos());
		    String idParameter = request.getParameter("id");
		    if (idParameter != null && !idParameter.isEmpty()) {
		        int id = Integer.parseInt(idParameter);
		        r.excluirProduto(id);
		    }
		    request.setAttribute("listaProdutos", listarProdutos());
		    RequestDispatcher rd = request.getRequestDispatcher("deletar.jsp");
		    rd.forward(request, response);
		    
		}
		
		
		
		if (path.equals("/alterar")) {
		    String idStr = request.getParameter("id");
		    if (idStr != null && !idStr.isEmpty()) {
		        int id = Integer.parseInt(idStr);
		        Produto produtoExistente = r.consultarProdutoPorId(id);
		        if (produtoExistente != null) {
		            String nome = request.getParameter("nome");
		            String categoria = request.getParameter("categoria");
		            float valor = Float.parseFloat(request.getParameter("valor"));
		            int quantidade = Integer.parseInt(request.getParameter("quantidade"));
		            Produto produtoAtualizado = new Produto(id, produtoExistente.getCodigo(), nome, categoria, valor, quantidade);
		            r.atualizarProduto(produtoAtualizado);
		            request.setAttribute("mensagem", "Produto atualizado com sucesso!");
		        } else {
		            request.setAttribute("mensagem", "Produto n�o encontrado!");
		        }
		    }
		    request.setAttribute("listaProdutos", listarProdutos());
		    RequestDispatcher rd = request.getRequestDispatcher("alterar.jsp");
		    rd.forward(request, response);
		}







		
	}
	protected List<Produto>listarProdutos() {
		List<Produto> listaProdutos = new ArrayList<>();
		listaProdutos = r.consultarProdutos();
		return listaProdutos;
	}
}
