package scalalab3.chatbotengine.model.common

import scalalab3.chatbotengine.model.inbound.messagecontent.media.PhotoSize


case class UserProfilePhotos(
    totalCount: Integer,
    photos: Array[Array[PhotoSize]])