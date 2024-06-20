package dev.kaccelero.routers

import io.ktor.server.http.content.*
import io.swagger.codegen.v3.*
import io.swagger.codegen.v3.generators.html.StaticHtml2Codegen
import java.nio.file.Files

class OpenAPIRouter(
    private val prefix: String = "/docs",
    private val generator: Generator = DefaultGenerator(),
    private val codegen: CodegenConfig = StaticHtml2Codegen(),
    private val opts: ClientOptInput = ClientOptInput(),
) : IRouter {

    override fun createRoutes(root: IRoute, openAPI: IOpenAPI?) {
        if (root !is KtorRoute || openAPI !is SwaggerOpenAPI?) return

        val dir = Files.createTempDirectory("docs").toFile()

        generator.opts(opts.apply {
            this.config = codegen.apply {
                outputDir = dir.absolutePath
            }
            this.opts = ClientOpts()
            this.openAPI = openAPI?.openAPI
        })
        generator.generate()

        root.route.staticFiles(prefix, dir)
    }

}
