package com.jorge.UI

import com.jorge.PTASorter.IPTANoticeSorter
import com.jorge.PTASorter.PTANoticeSorterImp
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.event.*
import java.io.File
import java.time.LocalDateTime
import javax.swing.*
import javax.swing.JFrame.EXIT_ON_CLOSE


class ConfigImp: ActionListener, IConfig {

    private val frame = JFrame("Property Tax Associates")

    private val panelYear = JPanel(GridBagLayout())
    private val panelPIDRef = JPanel(GridBagLayout())
    private val panelSearch = JPanel(GridBagLayout())
    private val panelDestination = JPanel(GridBagLayout())
    private val panelOutput = JPanel(GridBagLayout())
    private val panelSearchButton = JPanel(GridBagLayout())

    private val c = GridBagConstraints()
    private val cStart = GridBagConstraints()
    private val cMiddle = GridBagConstraints()
    private val cEnd = GridBagConstraints()
    private val cErrorRow = GridBagConstraints()

    private val labelErrorYear= JLabel("<html><font size='2' color=red>Error! Year field must not be empty.</font></html>")
    private val labelErrorPIDRef = JLabel("<html><font size='2' color=red>Error! Active PIDs field must not be empty.</font></html>")
    private val labelErrorSource = JLabel("<html><font size='2' color=red>Error! Source field must not be empty.</font></html>")
    private val labelErrorDestination = JLabel("<html><font size='2' color=red>Error! Destination field must not be empty.</font></html>")
    private val labelErrorOutput = JLabel("<html><font size='2' color=red>Error! Output field must not be empty.</font></html>")

    private val labelYear = JLabel("Year: ")
    private val fieldYear = JTextField(4)
    private val trailingLabelYear = JLabel()

    private val labelPIDRef = JLabel("Current PIDs")
    private val fieldPIDRef = JTextField(30)
    private val searchPIDRef = JButton("...")

    private val labelSource = JLabel("Source: ")
    private val fieldSource = JTextField(30)
    private val searchSource = JButton("...")

    private val labelDestination = JLabel("Destination: ")
    private val fieldDestination = JTextField(30)
    private val searchDestination = JButton("...")

    private val labelOutput = JLabel("Output File: ")
    private val fieldOutput = JTextField(30)
    private val searchOutput = JButton("...")

    private val button = JButton("Sort")

    override fun init(){

        frame.layout = GridBagLayout()

        fieldYear.text = LocalDateTime.now().year.toString()

        button.addActionListener(this)
        searchPIDRef.addActionListener(SearchButtonListener())
        searchSource.addActionListener(SearchButtonListener())
        searchDestination.addActionListener(SearchButtonListener())
        searchOutput.addActionListener(SearchButtonListener())

        val labelDimension = Dimension(30, 25)

        labelYear.minimumSize = labelDimension
        labelYear.maximumSize = labelDimension
        labelYear.preferredSize = labelDimension

        trailingLabelYear.minimumSize = Dimension(200, 25)
        trailingLabelYear.maximumSize = Dimension(200, 25)
        trailingLabelYear.preferredSize = Dimension(200, 25)

        labelPIDRef.minimumSize = labelDimension
        labelPIDRef.maximumSize = labelDimension
        labelPIDRef.preferredSize = labelDimension

        labelSource.minimumSize = labelDimension
        labelSource.maximumSize = labelDimension
        labelSource.preferredSize = labelDimension

        labelDestination.maximumSize = labelDimension
        labelDestination.minimumSize = labelDimension
        labelDestination.preferredSize = labelDimension

        labelErrorYear.maximumSize = labelDimension
        labelErrorYear.minimumSize = labelDimension
        labelErrorYear.preferredSize = labelDimension

        labelErrorPIDRef.maximumSize = labelDimension
        labelErrorPIDRef.minimumSize = labelDimension
        labelErrorPIDRef.preferredSize = labelDimension

        labelErrorSource.maximumSize = labelDimension
        labelErrorSource.minimumSize = labelDimension
        labelErrorSource.preferredSize = labelDimension

        labelErrorDestination.maximumSize = labelDimension
        labelErrorDestination.minimumSize = labelDimension
        labelErrorDestination.preferredSize = labelDimension

        labelOutput.maximumSize = labelDimension
        labelOutput.minimumSize = labelDimension
        labelOutput.preferredSize = labelDimension

        labelErrorYear.isVisible = false
        labelErrorPIDRef.isVisible = false
        labelErrorSource.isVisible = false
        labelErrorDestination.isVisible = false
        labelErrorOutput.isVisible = false

        cStart.fill = GridBagConstraints.HORIZONTAL
        cStart.gridx = 0
        cStart.gridy = 0
        cStart.ipadx = 20

        cMiddle.fill = GridBagConstraints.HORIZONTAL
        cMiddle.gridx = 1
        cMiddle.gridy = 0

        cErrorRow.fill = GridBagConstraints.HORIZONTAL
        cErrorRow.gridx = 0
        cErrorRow.gridy = 1
        cErrorRow.gridwidth = 3
        cErrorRow.weightx = 1.0

        panelYear.add(labelYear, cStart)
        panelYear.add(fieldYear, cMiddle)
        panelYear.add(labelErrorYear, cErrorRow)

        cStart.fill = GridBagConstraints.HORIZONTAL
        cStart.gridx = 0
        cStart.gridy = 0
        cStart.weightx = 1.0
        cStart.ipadx = 20

        cMiddle.fill = GridBagConstraints.HORIZONTAL
        cMiddle.gridx = 1
        cMiddle.gridy = 0
        cMiddle.gridwidth = 2
        cMiddle.weightx = 0.5

        cEnd.fill = GridBagConstraints.HORIZONTAL
        cEnd.gridx = 3
        cEnd.gridy = 0
        cEnd.anchor = GridBagConstraints.LINE_END
        cEnd.weightx = .25

        cErrorRow.fill = GridBagConstraints.HORIZONTAL
        cErrorRow.gridx = 0
        cErrorRow.gridy = 1
        cErrorRow.gridwidth = 3
        cErrorRow.weightx = 1.0

        panelPIDRef.add(labelPIDRef, cStart)
        panelPIDRef.add(fieldPIDRef, cMiddle)
        panelPIDRef.add(searchPIDRef, cEnd)
        panelPIDRef.add(labelErrorPIDRef, cErrorRow)

        panelSearch.add(labelSource, cStart)
        panelSearch.add(fieldSource, cMiddle)
        panelSearch.add(searchSource, cEnd)
        panelSearch.add(labelErrorSource, cErrorRow)

        panelDestination.add(labelDestination, cStart)
        panelDestination.add(fieldDestination, cMiddle)
        panelDestination.add(searchDestination, cEnd)
        panelDestination.add(labelErrorDestination, cErrorRow)

        panelOutput.add(labelOutput, cStart)
        panelOutput.add(fieldOutput, cMiddle)
        panelOutput.add(searchOutput, cEnd)
        panelOutput.add(labelErrorOutput, cErrorRow)

        cEnd.fill = GridBagConstraints.HORIZONTAL
        cEnd.gridx = 3
        cEnd.gridy = 0
        cEnd.anchor = GridBagConstraints.LINE_END
        cEnd.weightx = 0.0
        panelSearchButton.add(button, cEnd)

        c.fill = GridBagConstraints.HORIZONTAL
        c.gridx = 0
        c.gridy = 0
        c.gridwidth = 4
        c.weightx = 1.0
        frame.add(panelYear, c)

        c.fill = GridBagConstraints.HORIZONTAL
        c.gridx = 0
        c.gridy = 1
        c.weightx = 1.0
        frame.add(panelPIDRef, c)

        c.fill = GridBagConstraints.HORIZONTAL
        c.gridx = 0
        c.gridy = 2
        c.weightx = 1.0
        frame.add(panelSearch, c)

        c.fill = GridBagConstraints.HORIZONTAL
        c.gridx = 0
        c.gridy = 3
        c.weightx = 1.0
        frame.add(panelDestination, c)

        c.fill = GridBagConstraints.HORIZONTAL
        c.gridx = 0
        c.gridy = 4
        c.weightx = 1.0
        frame.add(panelOutput, c)

        c.fill = GridBagConstraints.HORIZONTAL
        c.gridx = 0
        c.gridy = 5
        c.weightx = 1.0
        frame.add(panelSearchButton, c)

        frame.defaultCloseOperation = EXIT_ON_CLOSE
        frame.setSize(700, 250)
        frame.isVisible = true
    }

    override fun actionPerformed(e: ActionEvent) {

        labelErrorYear.isVisible = false
        labelErrorPIDRef.isVisible = false
        labelErrorSource.isVisible = false
        labelErrorDestination.isVisible = false
        labelErrorOutput.isVisible = false

        val year = if(fieldYear.text != null && fieldYear.text != ""){
                fieldYear.text?.toInt()
        } else {
            val currYear = LocalDateTime.now().year
            fieldYear.text = currYear.toString()
            currYear
        }

        val ref = fieldPIDRef.text
        val source = fieldSource.text
        val destination = fieldDestination.text
        val output = fieldOutput.text


        if(year != null && ref != null && ref != "" && source != null && source != "" && destination != null && destination != "" && output != null && output != ""){
            val ptans: IPTANoticeSorter = PTANoticeSorterImp(year!!, File(ref), File(source), File(destination), File(output))
            ptans.init()
        }
        else{
            if(year == null) labelErrorYear.isVisible = true
            if(ref == null || ref == "" ) labelErrorPIDRef.isVisible = true
            if(source == null || source == "") labelErrorSource.isVisible = true
            if(destination == null || destination == "" ) labelErrorDestination.isVisible = true
            if(output == null || output == "" ) labelErrorOutput.isVisible = true
        }
    }

    private inner class SearchButtonListener: ActionListener{
        override fun actionPerformed(e: ActionEvent){
            val fc = JFileChooser()
            var f : File
            var filepath = ""
            fc.fileSelectionMode = if(e.source != searchPIDRef) JFileChooser.DIRECTORIES_ONLY else JFileChooser.FILES_ONLY
            val i = fc.showOpenDialog(JFrame())
            if(i==JFileChooser.APPROVE_OPTION){
                f = fc.selectedFile
                filepath = f.path
            }
            when(e.source){
                searchPIDRef -> {
                    fieldPIDRef.text = filepath
                }
                searchSource -> {
                    fieldSource.text = filepath
                }
                searchDestination -> {
                    fieldDestination.text = filepath
                }
                searchOutput -> {
                    fieldOutput.text = filepath
                }
                else -> {

                }
            }
        }
    }

}
