package org.chotoo.main

import org.chotoo.fileproc.Initiator
import scalax.io.Resource
import scalax.file.Path
import org.chotoo.fileproc.CsvProcessor
import java.util.Properties
import java.io.FileInputStream

/**
 * Main File which kicks off the application.
 * As of now it takes two parameters as input.
 * First argument - FileName
 * Second argument - search String
 * Then initiates the file search calling Initiator.scala
 *
 * @author MohanMuddana
 */
object Chotoo extends App {

  /* Entry point of the application */
  println("Chotoo boostrapping....")

  val usage = "Directory Path and SearchString not supplied as arguments" +
    " \n Usage:- run Directory_Path properties_File_containing_SearchStrings"

  def findStrings(dirPath: String, propFileName: String) = {
    Path("entriesfound.txt").delete(true) //TODO: find some other alternatives
    val inputStrings = Path(propFileName).lines(includeTerminator = false).dropWhile { _.isEmpty }.
      takeWhile { _.nonEmpty }.toList
    inputStrings.foreach(kickoff(dirPath, _))
  }
  //  findStrings("SearchStrings.properties","/home/ispun068/workspace/chotoo")

  /* Fetching Value from csv property file and calling CSV parser  */
  val prop = new Properties()
  prop.load(new FileInputStream("/home/ispun068/workspace/chotoo/label.poperties"))
  val filePath = prop.getProperty("filepath")
  val key = prop.getProperty("key")
  println("path is " + filePath + " key is " + key)
  val file = Path(filePath, '/') // file path of a CSV file in the system
  println(CsvProcessor.processCsvFile(file, key))

  /* Initiate file processing */
  def kickoff(pathToDir: String, searchString: String) {
    val initiator = new Initiator(pathToDir, searchString)
    initiator.initiate()
  }
}