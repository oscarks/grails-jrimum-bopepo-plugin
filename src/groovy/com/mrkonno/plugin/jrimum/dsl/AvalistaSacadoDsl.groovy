package com.mrkonno.plugin.jrimum.dsl

import org.jrimum.domkee.financeiro.banco.febraban.SacadorAvalista;

class AvalistaSacadoDsl extends EntidadeCobrancaDsl {
    AvalistaSacadoDsl() {
    }
    AvalistaSacadoDsl(String nome,String doc, clos) {
        instance=new SacadorAvalista(nome,doc)
        clos.delegate=this
        clos.call()
    } 
}
