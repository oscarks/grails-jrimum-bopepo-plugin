class JrimumBopepoGrailsPlugin {
    // the plugin version
    def version = "0.4"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3.7 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp",
			"grails-app/views/index.gsp",
			"grails-app/views/boleto/index.gsp",
			"grails-app/controller/com/mrkonno/plugin/jrimum/example/BoletoController.groovy"
    ]

    // TODO Fill in these fields
    def author = "Oscar Konno Sampaio"
    def authorEmail = "oscarks@gmail.com"
    def title = "JRimum Bopepo Plugin"
    def description = '''\\
This plugin allow to create Boletos Bancarios for Banks of Brazil using the Jrimum Bopepo library
It define a DSL to declare the Boleto's data structure and same methods to easy render in web interface
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/jrimum-bopepo"

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before 
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
}
