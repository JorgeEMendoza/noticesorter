package com.jorge.DirectoryMaker

import java.io.File
import java.time.LocalDateTime

class DirectoryMakerImp(private val ROOT: String): IDirectoryMaker {


    override fun createDirectory(pid: String): Boolean{

        var success = false

        try{

            var currYear = LocalDateTime.now().year

            if(!File("$ROOT").exists())
            {
                File("$ROOT").mkdir()

            }

            val dir = File("$ROOT/${pid}")

            if(!dir.exists()){
                dir.mkdir()
                File("$ROOT/${pid}/Notices").mkdir()
            }

            success = true
        }
        catch(e: Exception){
            println(e.message)
        }

        return success
    }

}