package com.mrkonno.plugin.jrimum

class Boleto {
	def boleto
	
	Boleto(org.jrimum.bopepo.Boleto boleto) {
		this.boleto=boleto
	}
	
	byte[] getBytes() {
		def bview=new org.jrimum.bopepo.view.BoletoViewer(boleto)
		bview.getPdfAsByteArray()
	} 
	
	File pdf(String fn) {
		def bview=new org.jrimum.bopepo.view.BoletoViewer(boleto)
		bview.getPdfAsFile(fn)
	}
	ByteArrayOutputStream getStream() {
		def bview=new org.jrimum.bopepo.view.BoletoViewer(boleto)
		bview.getPdfAsStream()
	}
	
}
