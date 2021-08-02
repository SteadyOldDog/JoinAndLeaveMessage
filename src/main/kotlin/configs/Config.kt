package online.bikinibottom.Configs

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.value

object Config : AutoSavePluginConfig("Config"){
    val joinMessage by value("欢迎新群主加入，@我可查看帮助")
    val leaveMessage by value("有人跑路了~")
    val enabledBot by value(839516647L)
    val enbleAt by value(true)
    val enblePic by value(true)
}