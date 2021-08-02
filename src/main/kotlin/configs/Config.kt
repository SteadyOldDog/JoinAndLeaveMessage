package online.bikinibottom.Configs

import net.mamoe.mirai.console.data.AutoSavePluginConfig

object Config : AutoSavePluginConfig("configs"){
    val joinMessage : String ="欢迎新群主加入，@我可查看帮助"
    val leaveMessage : String ="有人跑路了~"
    val enabledBot:Long = 839516647L
    val enbleAt:Boolean = true
    val enblePic:Boolean = true
}