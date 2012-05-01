package com.mrkonno.plugin.jrimum.dsl

import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.bopepo.BancosSuportados;

class ContaBancariaDsl {
    def contaBancariaInstance
    
    ContaBancariaDsl(clos) {
        contaBancariaInstance=new ContaBancaria()
        clos.delegate=this
        clos.call()
    }
    
    def banco(String b) { contaBancariaInstance.banco=BancosSuportados."${b.toUpperCase()}".create()}
    def banco(BancosSuportados b) {contaBancariaInstance.banco=b.create()}
    def conta(int numero, String dv) {contaBancariaInstance.numeroDaConta=new NumeroDaConta(numero,dv)}
    def carteira(int c) { contaBancariaInstance.carteira=new Carteira(c) }
    def carteira(int c, t) { contaBancariaInstance.carteira=new Carteira(c,t) }
    def agencia(int numero, String dv) { contaBancariaInstance.agencia=new Agencia(numero,dv) }
    
}