package com.jorge.DirectoryMaker

import java.io.File

interface IDirectoryMaker {

    fun createDirectory(file: String): Boolean
}