package com.mrkonno.plugin.jrimum.dsl

class EntidadeCobrancaDsl {
    def instance
    def endereco(clos) {
        def end=EnderecoDsl.endereco(clos)
        instance.addEndereco(end)
    }
}
