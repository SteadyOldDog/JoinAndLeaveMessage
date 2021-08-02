package online.bikinibottom

import net.mamoe.mirai.Bot
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.contact.Contact.Companion.uploadImage
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.MemberJoinEvent
import net.mamoe.mirai.event.events.MemberLeaveEvent
import net.mamoe.mirai.message.data.*
import net.mamoe.mirai.utils.info
import online.bikinibottom.Configs.Config
import online.bikinibottom.image.ImagePath
import online.bikinibottom.image.ImagePath.imageDirectory
import online.bikinibottom.image.ImagePath.jpg
import java.io.*

object JoinAndLeaveMessage : KotlinPlugin(
    JvmPluginDescription(
        id = "online.bikinibottom.joinandleavemessage",
        name = "JoinAndLeaveMessage",
        version = "1.0.0",
    ) {
        author("BlackWings")
        info("""小巧的自定义入群和退群消息的mirai-console插件""")
    }
) {
    override fun onEnable() {
        logger.info { "JoinAndLeaveMessage Plugin loaded" }

        GlobalEventChannel.subscribeAlways<MemberJoinEvent> { event ->
            //1.监听到群员已加入群后，在开启了的群里发送一条消息，消息内容为Config.joinMessage
            Bot.instances.forEach {
                //1.1 判断哪个机器人开启了本插件功能，负责执行欢迎
                if (it.id== Config.enabledBot) {
                    val defaultWelcomeJpg = File("$imageDirectory\\welcome.jpg")
                    //1.1.1 既开启了at，又开启了图片发送
                    if (Config.enbleAt && Config.enblePic) {
                        var messageChain = messageChainOf(PlainText(Config.joinMessage))
                        //通过遍历判断一下欢迎图片是否存在，不存在则从resource资源里将默认图片导出
                        ImagePath.picPaths.forEach {
                            if (it.exists()){
                                //存在则构建一条messageChain，内容为at+欢迎消息+图片
                                messageChain = messageChainOf(
                                    At(event.member.id),
                                    PlainText(Config.joinMessage),
                                    event.group.uploadImage(it,"png"))
                            }else{//不存在则将resources/welcome.jpg复制到imageDirectory目录并构建messageChain
                                if (!imageDirectory.exists()) imageDirectory.mkdir()
                                defaultWelcomeJpg.createNewFile()

                                val bis = BufferedInputStream(getResourceAsStream("welcome.jpg"))
                                val bfos = BufferedOutputStream(FileOutputStream(defaultWelcomeJpg))

                                var len = 0
                                val buf = ByteArray(1024)
                                while (true){
                                    len = bis.read(buf)
                                    if (len == -1) break
                                    bfos.write(buf,0,len)
                                }

                                bis.close()
                                bfos.close()

                                messageChain = messageChainOf(
                                    At(event.member.id),
                                    PlainText(Config.joinMessage),
                                    event.group.uploadImage(defaultWelcomeJpg,"png"))
                            }


                        }
                        //发送消息
                        event.group.sendMessage(messageChain)
                    }
                    //1.1.2 未开启at，只开启了图片发送
                    if (!Config.enbleAt && Config.enblePic) {
                        var messageChain = messageChainOf(PlainText(Config.joinMessage))
                        //通过遍历判断一下欢迎图片是否存在，不存在则从resource资源里将默认图片导出
                        ImagePath.picPaths.forEach {
                            if (it.exists()){
                                //存在则构建一条messageChain，内容为欢迎消息+图片
                                messageChain = messageChainOf(
                                    PlainText(Config.joinMessage),
                                    event.group.uploadImage(it,"png"))
                            }else{//不存在则将resources/welcome.jpg复制到imageDirectory目录并构建messageChain
                                if (!imageDirectory.exists()) imageDirectory.mkdir()
                                defaultWelcomeJpg.createNewFile()

                                val bis = BufferedInputStream(getResourceAsStream("welcome.jpg"))
                                val bfos = BufferedOutputStream(FileOutputStream(defaultWelcomeJpg))

                                var len = 0
                                val buf = ByteArray(1024)
                                while (true){
                                    len = bis.read(buf)
                                    if (len == -1) break
                                    bfos.write(buf,0,len)
                                }

                                bis.close()
                                bfos.close()

                                messageChain = messageChainOf(
                                    PlainText(Config.joinMessage),
                                    event.group.uploadImage(defaultWelcomeJpg,"png"))
                            }


                        }
                        //发送消息
                        event.group.sendMessage(messageChain)
                    }
                    //1.1.3 未开启at，未开启图片发送
                    if ((!Config.enbleAt) && (!Config.enblePic)){
                        event.group.sendMessage(Config.joinMessage)
                    }
                }
            }

        }

        GlobalEventChannel.subscribeAlways<MemberLeaveEvent> { event ->
            //2.监听到群员退出群后，在开启了功能的群里发送一条消息，消息内容为Config.leaveMessage
            //2.1判断群是否开启，
            Bot.instances.forEach {
                //判断哪个机器人开启了本插件功能，负责执行
                if (it.id== Config.enabledBot) {
                    event.group.sendMessage(Config.leaveMessage)
                    event.group.sendMessage(At(event.member.id) + event.member.nick)
                }
            }

        }
    }
}