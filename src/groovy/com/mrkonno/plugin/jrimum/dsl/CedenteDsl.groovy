package com.mrkonno.plugin.jrimum.dsl

import org.jrimum.domkee.financeiro.banco.febraban.Cedente;

class CedenteDsl extends EntidadeCobrancaDsl {
    CedenteDsl() {
    }
    CedenteDsl(String nome,String doc, clos) {
        instance=new Cedente(nome,doc)
        clos.delegate=this
        clos.call()
    } 
}