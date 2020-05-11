package com.jorge.FileLister

import org.apache.pdfbox.multipdf.Splitter
import org.apache.pdfbox.pdmodel.PDDocument
import java.io.File

class FileListerImp(private val ROOT: String): IFileLister {

    override fun getFilesFromFolder(): Array<File>{
        val splitter = Splitter()
        val folder = File(ROOT)
        var files = folder.listFiles().filter { file -> file.extension == "pdf"}.toTypedArray()
        for(file in files){
            val doc = PDDocument.load(file)
            if(doc.numberOfPages > 1){
                val pages = splitter.split(doc)
                val iterator = pages.listIterator()
                for(page in iterator){
                    page.save("$ROOT/${file.nameWithoutExtension}_${iterator.nextIndex()}.pdf")
                    page.close()
                }
                file.delete()
            }
            doc.close()
        }

        files = folder.listFiles().filter { file -> file.extension == "pdf"}.toTypedArray()
        val noOfFiles = folder.listFiles().size
        println("Found $noOfFiles ${if(noOfFiles == 1) "file" else "files" }")
        return files
    }
}