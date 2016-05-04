package scalalab3.chatbotengine.model

case class Message(val id: Long, val from: User, val date: Integer, val chat: Chat, val forwardFrom: User,
                   val forwardDate: Integer, val replyToMessage: Message, val text: String,
                   val entities: Array[MessageEntity], val audio: Audio, val document: Document,
                   val photo: Array[PhotoSize], val sticker: Sticker, val video: Video, val voice: Voice,
                   val caption: String, val contact: Contact,
                   val location: Location, val venue: Venue, val newChatMember: User, val leftChatMember: User,
                   val newChatTitle: String, val newChatPhoto: Array[PhotoSize], val deleteChatPhoto: Boolean,
                   val groupChatCreated: Boolean, val superGroupChatCreated: Boolean, val channelChatCreated: Boolean,
                   val migrateToChatId: Integer, val migrateFromChatId: Integer, val pinnedMessage: Message)