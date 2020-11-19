package com.archeroes.domain.enums;

public enum ContentStatus {
	
	STANDBY(1, "StandBy"), APPROVED(2, "Approved"), REFUSED(3, "Refused");

	private int cod;
	private String descricao;

	private ContentStatus(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static ContentStatus toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(ContentStatus x : ContentStatus.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id invalido " + cod );
	}
}
