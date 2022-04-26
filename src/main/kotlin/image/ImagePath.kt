package online.bikinibottom.image

import online.bikinibottom.JoinAndLeaveMessage
import java.io.File

object ImagePath {
    val imageDirectory = File("${JoinAndLeaveMessage.dataFolderPath}\\image")
    val gif = File("$imageDirectory\\welcome.gif")
    val png = File("$imageDirectory\\welcome.png")
    val jpg = File("$imageDirectory\\welcome.jpg")
    val bmp = File("$imageDirectory\\welcome.bmp")
    val picPaths :Array<File> = arrayOf(gif,png,jpg,bmp)
}