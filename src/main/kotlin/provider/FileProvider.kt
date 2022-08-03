package provider

import exceptions.FileException
import java.io.FileNotFoundException
import java.io.FileReader

const val FILENAME = "questionnaire.txt"

class FileProvider {
    fun readFile(): List<String> {
        return try {
            FileReader(FILENAME).readLines()
        } catch (ex: FileNotFoundException) {
            println(ex.message)
            throw FileException("File $FILENAME not found")
        }
    }
}
