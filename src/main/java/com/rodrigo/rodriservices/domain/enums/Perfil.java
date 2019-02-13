package com.rodrigo.rodriservices.domain.enums;

public enum Perfil {
	
	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
		
	private int codigo;
	private String descricao;
	
	private Perfil(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	/*
	 * Metodo que recebe um código e retorna um objeto 'Perfil'
	 * instanciado.(Retorna baseado no código passado).
	 */
	public static Perfil toEnum(Integer codigo) {

		if (codigo == null) {
			return null;
		}

		// Esse for percorre todos os valores possíveis deste Enum 'TipoCliente'
		for (Perfil x : Perfil.values()) {
			if (codigo.equals(x.getCodigo())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id Inválido: " + codigo);
	}
	
}
