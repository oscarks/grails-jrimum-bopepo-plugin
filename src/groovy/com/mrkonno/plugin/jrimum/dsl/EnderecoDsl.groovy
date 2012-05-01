package com.mrkonno.plugin.jrimum.dsl

import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import com.mrkonno.plugin.jrimum.MeuCEP

class EnderecoDsl {
    def enderecoInstance
    static def endereco(clos) {
        def end=new EnderecoDsl(enderecoInstance:new Endereco())
        clos.delegate=end
        clos.call()
        end.enderecoInstance
    }
    def uf(String u) { enderecoInstance.uf=UnidadeFederativa."${u.toUpperCase()}" }
    def localidade(String l) { enderecoInstance.localidade=l }
    def cep(String cep) { enderecoInstance.cep=new MeuCEP(cep) }
	def cep(Integer prefixo,Integer sufixo) { 
		enderecoInstance.cep=new MeuCEP()
		enderecoInstance.cep.prefixo=prefixo
		enderecoInstance.cep.sufixo=sufixo
	}
    def bairro(String b) {enderecoInstance.bairro=b }
    def logradouro(String l) { enderecoInstance.logradouro = l }
    def numero(String n) { enderecoInstance.numero=n }
    
}