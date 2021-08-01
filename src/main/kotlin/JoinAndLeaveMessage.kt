package online.bikinibottom

import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info

object JoinAndLeaveMessage : KotlinPlugin(
    JvmPluginDescription(
        id = "online.bikinibottom.joinandleavemessage",
        name = "JoinAndLeaveMessage",
        version = "1.0-SNAPSHOT",
    ) {
        author("BlackWings")
        info("""小巧的自定义入群和退群消息的mirai-console插件""")
    }
) {
    override fun onEnable() {
        logger.info { "Plugin loaded" }
    }
}