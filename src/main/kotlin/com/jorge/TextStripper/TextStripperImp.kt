package com.jorge.TextStripper

import net.sourceforge.tess4j.Tesseract
import net.sourceforge.tess4j.util.LoadLibs
import java.io.File as File

class TextStripperImp(private val ROOT: String): ITextStripper {


    private val arrayUnacceptable = charArrayOf(' ', '\n')
    private val tessDataFolder = LoadLibs.extractTessResources("tessdata")
    private val tess = Tesseract()

    override fun init(){
        tess.setDatapath(tessDataFolder.absolutePath)
        tess.setLanguage("eng")
    }

    override fun getText(file: File) : String{

        var text = tess.doOCR(file).substringAfter("Property ID: ")
        text = text.substring(0, text.indexOfAny(arrayUnacceptable))

        return text
    }

}