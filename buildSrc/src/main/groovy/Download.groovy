import org.apache.commons.io.FileUtils
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*


class Download extends DefaultTask {
    @Input
    String url
    @OutputFile
    String dest

    @TaskAction
    def download() {
        def file = new File(dest)
        file.parentFile.mkdirs()
        FileUtils.copyURLToFile(new URL(url), file)
    }
}
