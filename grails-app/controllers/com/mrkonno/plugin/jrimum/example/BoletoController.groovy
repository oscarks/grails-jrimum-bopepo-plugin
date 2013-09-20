package com.mrkonno.plugin.jrimum.example

import com.mrkonno.plugin.jrimum.dsl.BoletoDsl
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.hsbc.TipoIdentificadorCNR;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeCobranca;

class BoletoController {

    def index() { 
		
    }
	
	def generateBoleto() {
		def b=BoletoDsl.boleto {
			sacado('Mario Roberto','117.053.316-70') {
				endereco {
					uf 'PA'
					logradouro 'Av Santo Antonio'
					localidade 'Belem'
					cep '66039-203'
					bairro 'Centro'
					numero '302'
				}
			}
			cedente('ACME INC','18.410.773/0001-01') {
				endereco {
					uf 'PA'
					logradouro 'Av Conselheiro Furtado'
					localidade 'Belem'
					cep '660060-063'
					bairro 'Sao Braz'
					numero '3192'
				}
			}
			contaBancaria {
				banco 'BANCO_BRADESCO'
				conta 1310,'2'
				carteira 6
				agencia 2703,'2'
			}
			//dataVencimento new Date()+10
			numeroDocumento '000210'
			nossoNumero '42103929329'
			digitoNossoNumero '3'
			valor 100.00
			parametrosBancarios ([(TipoIdentificadorCNR.class.getName()):TipoIdentificadorCNR.SEM_VENCIMENTO])
			tipoTitulo TipoDeTitulo.DM_DUPLICATA_MERCANTIL
			localPagamento "Pagavel em qualquer Banco"
			instrucoes """Aceitar ate a data de vencimento
Apos o vencimento aceito apenas nas agencias do Bradesco
Cobrar multa de 7% e juros de mora de 0,03% ao dia"""
		}
		response.setContentType("application/pdf")
		response.setHeader("Content-disposition", "attachment;filename=teste.pdf")
		response.outputStream << b.bytes
		response.outputStream.flush()
	}
}
