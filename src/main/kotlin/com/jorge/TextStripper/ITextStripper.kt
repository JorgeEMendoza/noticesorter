package com.jorge.TextStripper

import org.apache.pdfbox.pdmodel.PDDocument
import java.io.File

interface ITextStripper {

    fun init()
    fun getText(file: File) : String

}