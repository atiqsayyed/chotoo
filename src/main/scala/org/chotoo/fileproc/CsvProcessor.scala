package org.chotoo.fileproc

import scalax.file.Path
import org.chotoo.util.CSV

/**
 * Parser to parse CSV Files
 * It uses a Util method to parse Csv files
 * @author AtiqSayyed
 */

object CsvProcessor {
  def processCsvFile(file: Path, key:String) = {
    val lines = file.lines(includeTerminator = false)
    val processedLines = CSV.parse(lines.mkString("\r\n"))
    val index = processedLines(0).indexOf(key)
    println(" index is  "+index)
    processedLines.map(_(index))
  }
}