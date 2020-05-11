package com.jorge.FileLister

import java.io.File

interface IFileLister {

    fun getFilesFromFolder() : Array<File>
}