package com.jorge.PTASorter

import com.jorge.DirectoryMaker.DirectoryMakerImp
import com.jorge.DirectoryMaker.IDirectoryMaker
import com.jorge.FileLister.FileListerImp
import com.jorge.FileLister.IFileLister
import com.jorge.ReferencePIDS.IReferencePIDs
import com.jorge.ReferencePIDS.ReferencePIDsImp
import com.jorge.TextStripper.ITextStripper
import com.jorge.TextStripper.TextStripperImp
import com.jorge.UI.ConfigImp
import java.io.File

class PTANoticeSorterImp(private val year: Int, private val ref: File, private val source: File, private val destination: File): IPTANoticeSorter {

    private lateinit var pidHarvester: IReferencePIDs
    private lateinit var referencePIDs: File
    private val fileReader: IFileLister = FileListerImp(source.toString())
    private val textStripper: ITextStripper = TextStripperImp(source.toString())
    private val directoryMaker: IDirectoryMaker = DirectoryMakerImp(destination.toString())

    override fun init(){

        textStripper.init()
        pidHarvester = ReferencePIDsImp(ref)
        val pids = pidHarvester.getReferencePIDs()


        var files = fileReader.getFilesFromFolder()

        var fileCount = files.size
        var successfulParse = 0
        var currentClients = 0

        var errorFiles = ArrayList<String>()

        for(file in files){
            val fileName = file.nameWithoutExtension
            try {
                val pid = textStripper.getText(file)

                successfulParse++

                if(pids.indexOf(pid) != -1){

                    if(directoryMaker.createDirectory(pid)){
                        file.renameTo(File("$destination/${pid}/Notices/${year} notice.pdf"))
                        currentClients++
                    }
                    else{
                        println("error")
                    }
                }else{
                    if(!File("$destination/rejects").exists()){
                        File("$destination/rejects").mkdir()
                    }

                    file.renameTo(File("$destination/rejects/$pid - 2020 notice"))
                }






            }
            catch(e : Exception){
                println("$fileName failed. Error: $e")
                errorFiles.add(fileName)
            }
        }

        println("Successfully parsed $successfulParse out of $fileCount")
        println("$currentClients notices belong to current clients out of $fileCount")
    }
}