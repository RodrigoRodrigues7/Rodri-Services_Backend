package com.rodrigo.rodriservices.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.rodrigo.rodriservices.services.validation.ClienteInsert;

@ClienteInsert
public class ClienteNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message="É Obrigatório o Preencimento do Nome")
	@Length(min=5, max=120, message="O Tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message="É Obrigatório o Preencimento da Senha")
	private String senha;
	
	@NotEmpty(message="É Obrigatório o Preencimento do Email")
	@Email(message="Email Inválido")
	private String email;
	
	@NotEmpty(message="É Obrigatório o Preencimento do Cpf ou Cnpj")
	private String cpfOuCnpj;
	private Integer tipo;
	
	@NotEmpty(message="É Obrigatório o Preencimento do Logradouro")
	private String logradouro;
	
	@NotEmpty(message="É Obrigatório o Preencimento do Número")
	private String numero;
	private String complemento;
	private String bairro;
	
	@NotEmpty(message="É Obrigatório o Preencimento do Cep")
	private String cep;

	private Integer cidadeId;
	
	@NotEmpty(message="É Obrigatório o Preencimento de ao menos 1(um) Telefones")
	private String telefone1;
	private String telefone2;
	private String telefone3;

	public ClienteNewDTO() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

}







