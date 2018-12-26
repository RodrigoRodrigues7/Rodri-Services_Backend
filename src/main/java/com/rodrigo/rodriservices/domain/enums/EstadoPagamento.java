package com.rodrigo.rodriservices.domain.enums;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int codigo;
	private String descricao;
	
	private EstadoPagamento(int codigo, String descricao) {
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
	 * Metodo que recebe um código e retorna um objeto 'EstadoPagamento'
	 * instanciado.(Retorna baseado no código passado).
	 */
	public static EstadoPagamento toEnum(Integer codigo) {

		if (codigo == null) {
			return null;
		}

		// Esse for percorre todos os valores possíveis deste Enum 'TipoCliente'
		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (codigo.equals(x.getCodigo())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id Inválido: " + codigo);
	}
	
}
