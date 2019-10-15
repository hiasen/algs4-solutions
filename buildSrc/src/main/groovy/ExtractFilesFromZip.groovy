import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*

import java.util.zip.ZipEntry
import java.util.zip.ZipFile


class ExtractFilesFromZip extends DefaultTask{
    @Input
    String zipArchive
    @Input
    String[] filenames
    @OutputDirectory
    String dest

    @TaskAction
    def unzip() {
        new File(dest).mkdirs()
        def zipFile = new ZipFile(zipArchive)
        for (filename in filenames) {
            def stream = zipFile.getInputStream(new ZipEntry(filename))
            def file2 = new File("$dest/$filename")
            def pv = new FileOutputStream(file2)
            stream.transferTo(pv)
        }
    }
}
