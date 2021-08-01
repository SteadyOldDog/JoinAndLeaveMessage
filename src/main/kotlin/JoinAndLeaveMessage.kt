package online.bikinibottom

import net.mamoe.mirai.Bot
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.MemberJoinEvent
import net.mamoe.mirai.event.events.MemberLeaveEvent
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.utils.info
import online.bikinibottom.Configs.Config

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
        logger.info { "JoinAndLeaveMessage Plugin loaded" }

        GlobalEventChannel.subscribeAlways<MemberJoinEvent> { event ->
            //监听到群员已加入群后，在开启了的群里发送一条消息，消息内容为Config.joinMessage

            Bot.instances.forEach {
                //判断哪个机器人开启了本插件功能，负责执行欢迎
                if (it.id== Config.enabledBot)
                //判断是否开启at加入者，开启了则at ta并发送欢迎消息
                    if (Config.enbleAt) {

//                        val messageChain : MessageChain =
                        event.group.sendMessage(At(event.member.id) +Config.joinMessage)
                    }//否则直接发送欢迎消息
                    else {
                        event.group.sendMessage(Config.joinMessage)
                    }
            }

        }
        GlobalEventChannel.subscribeAlways<MemberLeaveEvent> { event ->
            //监听到群员退出群后，在开启了功能的群里发送一条消息，消息内容为Config.leaveMessage
            //判断群是否开启，
            Bot.instances.forEach {
                //判断哪个机器人开启了功能
                if (it.id== Config.enabledBot) {
                    event.group.sendMessage(Config.leaveMessage)
                    val at = At(event.member.id)
                    event.group.sendMessage(at+event.member.nick)
                }
            }

        }
    }
}