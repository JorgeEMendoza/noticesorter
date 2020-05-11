package com.jorge.Report

import java.io.File

interface IReport {
    fun makeReport(processed: ArrayList<String>, rejected: ArrayList<String>, outputFile: File)
}