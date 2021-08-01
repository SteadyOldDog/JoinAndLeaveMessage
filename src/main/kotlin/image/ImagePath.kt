package online.bikinibottom.image

import online.bikinibottom.JoinAndLeaveMessage
import java.io.File

object ImagePath {
    val gif = File("${JoinAndLeaveMessage.dataFolderPath}\\welcome.gif")
    val png = File("${JoinAndLeaveMessage.dataFolderPath}\\welcome.png")
    val jpg = File("${JoinAndLeaveMessage.dataFolderPath}\\welcome.jpg")
}