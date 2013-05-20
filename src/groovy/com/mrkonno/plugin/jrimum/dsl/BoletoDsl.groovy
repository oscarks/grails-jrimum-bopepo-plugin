package com.mrkonno.plugin.jrimum.dsl

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.SacadorAvalista;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo.EnumAceite;
import org.jrimum.domkee.financeiro.banco.hsbc.TipoIdentificadorCNR;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeCobranca;
import org.jrimum.domkee.financeiro.banco.ParametrosBancariosMap;

class BoletoDsl {
    def sacadoDsl
    def cedenteDsl
    def avalistaDsl
    def contaBancariaDsl
    
    String numeroDocumento
    String nossoNumero
    String digitoNossoNumero
    BigDecimal valor
    Date dataDocumento=new Date()
    Date dataVencimento
    TipoDeTitulo tipoTitulo
    EnumAceite aceite=EnumAceite.A
    BigDecimal desconto=BigDecimal.ZERO
    BigDecimal deducao=BigDecimal.ZERO
    BigDecimal mora=BigDecimal.ZERO
    BigDecimal acrescimo=BigDecimal.ZERO
    def parametrosBancarios=new ParametrosBancariosMap()
    
    
    String localPagamento
    String instrucaoAoSacado
    String instrucoes

    def textosRs = [:]
    def textosFc = [:]
    
    def localPagamento(String s) {localPagamento=s}
    def instrucaoAoSacado(String s) {instrucaoAoSacado=s}
    def instrucoes(String s) {instrucoes=s}
    
    def parametrosBancarios(Map pars) {
        pars.each { k, v ->
            parametrosBancarios.adicione(k,v)    
        }
        parametrosBancarios
    }
    
    def createBoleto(Titulo t,contraApresentacao=false) {
        def boleto=new Boleto(t)
        if (localPagamento) boleto.localPagamento=localPagamento
        if (instrucaoAoSacado) boleto.instrucaoAoSacado=instrucaoAoSacado
        if (instrucoes) {
            def linst=instrucoes.split('\n')
            linst.eachWithIndex { s,i->
                if (i<8) {
                    boleto."instrucao${i+1}"=s
                }
            }
        }
		if (contraApresentacao) {
			boleto.addTextosExtras("txtFcDataVencimento", "CONTRA APRESENTAÇÃO");
			boleto.addTextosExtras("txtRsDataVencimento", "CONTRA APRESENTAÇÃO");
		}
        textosFc.each { item, valor->
            boleto.addTextosExtras("txtFc${item}",valor)
        }
        textosRs.each { item,valor->
            boleto.addTextosExtras("txtRs${item}",valor)
        }
        boleto
    }
    def createTitulo() {
        def contaBancaria=contaBancariaDsl?.contaBancariaInstance
        def cedente=cedenteDsl?.instance
        def sacado=sacadoDsl?.instance
        def avalista=avalistaDsl?.instance
        if (!contaBancaria)
            throw new RuntimeException("Conta Bancaria nao existente")
        if (!cedente)
            throw new RuntimeException("Cedente nao informado") 
        if (!sacado)
            throw new RuntimeException("Sacado nao informado")
        
        def titulo=new Titulo(contaBancaria,sacado,cedente, parametrosBancarios)
        if (avalista) titulo.sacadorAvalista=avalista
        if (numeroDocumento) titulo.numeroDoDocumento=numeroDocumento
        if (nossoNumero) titulo.nossoNumero=nossoNumero
        if (digitoNossoNumero) titulo.digitoDoNossoNumero=digitoNossoNumero
        if (numeroDocumento) titulo.numeroDoDocumento
        titulo.valor=valor
        titulo.dataDoDocumento=dataDocumento
		if (dataVencimento)
        	titulo.dataDoVencimento=dataVencimento
		else {
			titulo.dataDoVencimento=org.jrimum.bopepo.FatorDeVencimento.toDate(0)
		}
        titulo.tipoDeDocumento=tipoTitulo
        titulo.aceite=aceite
        titulo.desconto=desconto
        titulo.deducao=deducao
        titulo.mora=mora
        titulo.acrecimo=acrescimo
        titulo.parametrosBancariosMap=parametrosBancarios
        titulo
    }
    def numeroDocumento(String v) { numeroDocumento=v}
    def nossoNumero(String v) { nossoNumero=v}
    def digitoNossoNumero(String v) {digitoNossoNumero=v}
    def valor(v) { valor=BigDecimal.valueOf(v) }
    def dataDocumento(Date data) {dataDocumento=data}
    def dataVencimento(Date data) {dataVencimento=data}
    def tipoTitulo(TipoDeTitulo t) {tipoTitulo=t}
    def aceite(EnumAceite a) {aceite=a}
    def desconto(v) {desconto=BigDecimal.valueOf(v)}
    def deducao(v) {deducao=BigDecimal.valueOf(v)}
    def mora(v) {mora=BigDecimal.valueOf(v)}
    def acrescimo(v) {acrescimo==BigDecimal.valueOf(v)}
    
    def sacado(nome,doc,clos) {
        sacadoDsl= new SacadoDsl(nome,doc,clos)
    }
    
    def cedente(nome,doc,clos) {
        cedenteDsl = new CedenteDsl(nome,doc,clos)
    }
    
    def avalista(nome,doc,clos) {
        avalistaDsl =new AvalistaSacadoDsl(nome,doc,clos)
    }
    
    def contaBancaria(clos) {
        contaBancariaDsl=new ContaBancariaDsl(clos)
    }
    
    def textoReciboSacado(item,valor) {
        textosRs[item]=valor
    }
    def textoFichaCompensacao(item,valor) {
        textosFc[item]=valor
    }

    static def boleto(mainClos) {
        if(!mainClos) throw RuntimeException(message:"Definicao de boleto nao informado")
        else {
            def b=new BoletoDsl()
            mainClos.delegate=b
            mainClos.call()
			def contraApresentacao = b.dataVencimento ? false : true
            def titulo=b.createTitulo()
            def boleto=b.createBoleto(titulo,contraApresentacao)
            def meuBoleto=new com.mrkonno.plugin.jrimum.Boleto(boleto)
            meuBoleto
        }
    }
	
	
 }
