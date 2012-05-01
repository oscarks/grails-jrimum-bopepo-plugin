package com.mrkonno.plugin.jrimum

import org.jrimum.domkee.comum.pessoa.endereco.CEP

class MeuCEP extends CEP {
	MeuCEP() {
		super()
	}
	MeuCEP(String cep) {
		super(cep)
	}
	String toString() {
		if(cep) {
			return cep
		} else if (prefixo && sufixo) {
			"${String.format('%05d',prefixo)}-${sufixo}"
		} else if (prefixo && !sufixo) {
			"${String.format('%05d',prefixo)}-000"
		}
	}
}
