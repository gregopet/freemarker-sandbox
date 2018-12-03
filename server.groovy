@Grapes([
	@Grab('io.ratpack:ratpack-groovy:1.6.0-rc-2'),
	@Grab('org.slf4j:slf4j-simple:1.7.25')
])
import static ratpack.groovy.Groovy.ratpack

@GrabResolver(name='cantara', root='https://mvnrepo.cantara.no/content/repositories/releases/', m2Compatible='true')
@Grab(group='no.cantara', module='ratpack-freemarker', version='0.1')
import no.cantara.ratpack.freemarker.*


ratpack {
	
	bindings {
		module no.cantara.ratpack.freemarker.FreemarkerModule, { cfg -> cfg.templateLoadingPath = "templates" }
	}

	handlers {
		// Assets
		files {
			dir "js"
			path "js/uri"
		}
		files {
			dir "css"
			path "css/uri"
		}
	
		// HTML
		get { ctx ->
			def freemarkerConfig = ctx.get(FreemarkerRenderer).freemarkerConfig
			freemarkerConfig.directoryForTemplateLoading = new File("templates")
			
			def model= [
				name: 'dear developer'
			]
		
			render new FreemarkerModel("hello.ftl", model)
		}
	}
}
