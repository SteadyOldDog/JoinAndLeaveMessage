package online.bikinibottom.Configs

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object Config : AutoSavePluginConfig("Config"){
    @ValueDescription("加入群消息")
    val joinMessage by value("欢迎新群主加入，@我可查看帮助")
    @ValueDescription("离开群消息")
    val leaveMessage by value("有人跑路了~")
    @ValueDescription("处理此插件的Bot")
    val enabledBot by value(839516647L)
    @ValueDescription("是否开启at")
    val enbleAt by value(true)
    @ValueDescription("是否开启图片，图片路径在data/JoinAndLeaveMessage/image/welcome.jpg")
    val enblePic by value(true)
}