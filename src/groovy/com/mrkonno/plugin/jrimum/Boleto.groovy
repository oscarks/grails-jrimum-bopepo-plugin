package com.mrkonno.plugin.jrimum

class Boleto {
	def boleto
	def template
	
	Boleto(org.jrimum.bopepo.Boleto boleto) {
		this.boleto=boleto
	}
	Boleto(org.jrimum.bopepo.Boleto boleto,String template) {
		this.boleto=boleto
		this.template=template
	}
	
	def getBoletoView() {
		def bview
		if (template)
			bview=new org.jrimum.bopepo.view.BoletoViewer(boleto,template)
		else bview=new org.jrimum.bopepo.view.BoletoViewer(boleto)
		bview
	}
	
	byte[] getBytes() {
		def bview=boletoView
		bview.getPdfAsByteArray()
	} 
	
	File pdf(String fn) {
		def bview=boletoView
		bview.getPdfAsFile(fn)
	}
	ByteArrayOutputStream getStream() {
		def bview=boletoViewer
		bview.getPdfAsStream()
	}
	
}
