package com.jorge.ReferencePIDS

import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream

class ReferencePIDsImp(private val ref: File): IReferencePIDs {

    private lateinit var pids: ArrayList<String>

    override fun getReferencePIDs(): Array<String>{
        pids = ArrayList()

        val fis = FileInputStream(ref)
        val workbook = XSSFWorkbook(fis)
        val sheet = workbook.getSheetAt(0)
        val rowIterator = sheet.iterator()

        for(row in rowIterator){
            val cellIterator = row.cellIterator()
            for(cell in cellIterator){
                when(cell.cellType){
                    CellType.STRING -> pids.add(cell.stringCellValue)
                    CellType.NUMERIC -> pids.add(cell.numericCellValue.toString())
                    else -> println("Something else")
                }
            }

        }

        workbook.close()
        return pids.toTypedArray()
    }
}