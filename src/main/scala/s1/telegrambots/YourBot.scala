package s1.telegrambots
import com.bot4s.telegram.models
import com.bot4s.telegram.models.User
import s1.telegrambots.BasicBot
import s1.telegrambots.examples.AlarmBot.Bot.{Message, ajastus}
import s1.telegrambots.examples.GreetBot.Bot.tervehdi
import s1.telegrambots.examples.PhotoBot.Bot.pepperReply
import s1.telegrambots.examples.RememberBot.Bot.{Message, getUserFirstName, kerro}
import s1.telegrambots.examples.ReverseBot.Bot.nurinpain


import scala.util.{Sorting, Try, Using}
import scala.io.Source
import scala.collection.mutable.ListBuffer


object YourBot extends App:
    object Bot extends BasicBot:
        var AddingOn = false //Boolean for listening for messages while adding
        var RemovingOn = false //Boolean for listening for messages while removing
        var list = ListBuffer[String]() //list of products
        this.run()

        // Tarkistetaan, että lähti käyntiin
        println("Started the bot")

        def MessageHandler(msg: Message) = // Handles all messages
            var s = this.getString(msg)
            if(AddingOn && s.equals("End") == false){ //if Adding is on adds products to list until End is sent
                list += s
                list = list.sortWith(_.compareTo(_) < 0)
                this.writeMessage(ListString(), this.getChatId(msg))
            } else if(RemovingOn && s.equals("End") == false){ //if Removing is on adds products to list until End is sent
                if (Try(s.toInt).isSuccess && s.toInt < list.length) {
                    list.remove(s.toInt)
                    this.writeMessage(ListString(), this.getChatId(msg))
                } //If sended message is Integer and no longer than list
                else {
                    this.writeMessage("Wrong input", this.getChatId(msg)) // Reports wrong input
                }
            } else{
                Off() //set listeners off if End sended
            }

        def SayHello(msg: Message) =
            "Salam aleykum, " + this.getUserFirstName(msg)
        def Show(msg : Message)  = //Shows list
            Off()
            var str = ListString()
            str


        def Adding(msg: Message) = //Sets Adding on.
            Off()
            AddingOn = true
            "Send product you want to add. Send End if you finnished"

        def Clear(msg: Message) = //Clears list
            Off()
            list.clear()
            "List is cleared"


        def Removing(msg: Message) = //Sets Removing on
            Off()
            this.writeMessage(ListString(), this.getChatId(msg))
            RemovingOn = true

            "Send number to delete product from the list. Send End to stop."

        def Help(msg : Message) =
            Off()
            "/show to show whole list.\n" +
              "/clear to clear whole list.\n" +
              "/adding after this command next messages will be added to list until you send End.\n" +
              "/removing after this command you can delete items from the list by sending their index.\n" +
              "/alarmIn [minutes] sets alalrm to ring."

        def Off() =
            AddingOn = false
            RemovingOn = false

        def ListString() : String = {
            if (list.isEmpty) {
               RemovingOn = false
               return "The list is Empty"
            } else {
                var x = 0
                var str = ""
                for (i <- list) {
                    str = str + s"[$x] " + s"$i\n"
                    x = x + 1
                }
                return str
            }

        }


        def Alarm(message: Message): String = { //Sets alarm to ring in inputted minutes
            Off()
            val time = util.Try(getString(message).toInt).toOption
            time match {
                case None => "You did not give time limit"
                case Some(mintues) => {
                    scala.concurrent.Future {
                        Thread.sleep(mintues * 1000 * 60)
                        var str = "You wanted to by this:\n" + ListString()
                        writeMessage(str, getChatId(message))
                    }
                    "I will remind you your list in " + mintues + " minutes."
                }
            }
        }

        this.onUserCommand("start", SayHello)
        this.onUserCommand("help", Help)
        this.onUserCommand("show", Show)
        this.onUserCommand("clear", Clear)
        this.onUserCommand("adding", Adding)
        this.onUserCommand("removing", Removing)
        this.onUserMessage(MessageHandler)
        this.onUserCommand("alarmIn", Alarm)

    end Bot

    // Tämä rivi pyytää ja ajaa täten yllä olevan botin
    val bot = Bot
end YourBot
