package com.mrkonno.plugin.jrimum.dsl

import org.jrimum.domkee.financeiro.banco.febraban.Sacado;

class SacadoDsl extends EntidadeCobrancaDsl {
    SacadoDsl() {
    }
    SacadoDsl(String nome,String doc, clos) {
        instance=new Sacado(nome,doc)
        clos.delegate=this
        clos.call()
    } 
}
