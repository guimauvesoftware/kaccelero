package dev.kaccelero.routers

import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import io.swagger.codegen.v3.*
import io.swagger.codegen.v3.generators.html.StaticHtml2Codegen
import io.swagger.v3.oas.models.OpenAPI
import java.nio.file.Files

class OpenAPIRouter(
    private val prefix: String = "/docs",
    private val generator: Generator = DefaultGenerator(),
    private val codegen: CodegenConfig = StaticHtml2Codegen(),
    private val opts: ClientOptInput = ClientOptInput(),
) : IRouter {

    override fun createRoutes(root: Route, openAPI: OpenAPI?) {
        val dir = Files.createTempDirectory("docs").toFile()

        generator.opts(opts.apply {
            this.config = codegen.apply {
                outputDir = dir.absolutePath
            }
            this.opts = ClientOpts()
            this.openAPI = openAPI
        })
        generator.generate()

        root.staticFiles(prefix, dir)
    }

}
