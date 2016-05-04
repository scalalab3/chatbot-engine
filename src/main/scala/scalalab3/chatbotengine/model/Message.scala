package scalalab3.chatbotengine.model

case class Message(id: Long, date: Integer, chat: Chat, from: Option[User] = None,
                   forwardFrom: Option[User] = None, forwardDate: Option[Integer] = None,
                   replyToMessage: Option[Message] = None, text: Option[String] = None,
                   entities: Option[Array[MessageEntity]] = None, audio: Option[Audio] = None,
                   document: Option[Document] = None, photo: Option[Array[PhotoSize]] = None,
                   sticker: Option[Sticker] = None, video: Option[Video] = None, voice: Option[Voice] = None,
                   caption: Option[String] = None, contact: Option[Contact] = None, location: Option[Location] = None,
                   venue: Option[Venue] = None, newChatMember: Option[User] = None, leftChatMember: Option[User] = None,
                   newChatTitle: Option[String] = None, newChatPhoto: Option[Array[PhotoSize]] = None,
                   deleteChatPhoto: Option[Boolean] = None, groupChatCreated: Option[Boolean] = None,
                   superGroupChatCreated: Option[Boolean] = None, channelChatCreated: Option[Boolean] = None,
                   migrateToChatId: Option[Integer] = None, migrateFromChatId: Option[Integer] = None,
                   pinnedMessage: Option[Message] = None)