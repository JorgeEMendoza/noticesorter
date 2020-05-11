package com.jorge.Report

import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream

class ReportImp: IReport {

    override fun makeReport(processed: ArrayList<String>, rejected: ArrayList<String>, output: File) {
        var workbook = XSSFWorkbook()
        var sheet = workbook.createSheet("Processed PIDs")

        if(processed.size > 0){
            for((index, processedPID) in processed.withIndex()){
                val row = sheet.createRow(index)
                val cell = row.createCell(0)
                cell.setCellValue(processedPID)
            }

        }

        if(rejected.size > 0){
            sheet = workbook.createSheet("Rejected PIDs")
            for((index,reject) in rejected.withIndex()){
                val row = sheet.createRow(index)
                val cell = row.createCell(0)

                cell.setCellValue(reject)
            }
        }

        val fileOut = FileOutputStream("$output/ProcessedPIDs.xlsx")
        workbook.write(fileOut)
        fileOut.close()
        workbook.close()

    }
}