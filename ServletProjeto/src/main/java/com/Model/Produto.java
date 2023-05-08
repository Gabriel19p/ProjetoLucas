package com.Model;

public class Produto {
	private int id;
	private int codigo;
	private String nome;
	private String categoria;
	private float valor;
	private int quantidade;

	public Produto (int id,int codigo, String nome, String categoria, float valor, int quantidade) {
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.categoria = categoria;
		this.valor = valor;
		this.quantidade = quantidade;
	}
	public produto() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public void toString() {
		return "Produto [id=" + id +" codigo=" + codigo +", nome=" + nome + ", categoria=" + categoria+ ", valor=" + valor + ", quantidade=" + quantidade + "]";
	}
	
	}
