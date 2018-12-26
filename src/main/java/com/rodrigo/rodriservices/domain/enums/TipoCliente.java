package com.rodrigo.rodriservices.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "PessoaFísica"), 
	PESSOAJURIDICA(2, "PessoaJurídica");

	private int codigo;
	private String descricao;

	private TipoCliente(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	/*
	 * Só metodos 'GET', pois não se pode alterar um tipo enumarado apos sua
	 * instancia
	 */

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	/*
	 * Metodo que recebe um código e retorna um objeto 'TipoCliente'
	 * instanciado.(Retorna baseado no código passado).
	 */
	public static TipoCliente toEnum(Integer codigo) {

		if (codigo == null) {
			return null;
		}

		// Esse for percorre todos os valores possíveis deste Enum 'TipoCliente'
		for (TipoCliente x : TipoCliente.values()) {
			if (codigo.equals(x.getCodigo())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id Inválido: " + codigo);
	}

}
