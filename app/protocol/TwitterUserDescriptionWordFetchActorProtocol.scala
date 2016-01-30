package protocol

/**
  *
  */
object TwitterUserDescriptionWordFetchActorProtocol {

  case class FetchUserDescriptionWordsForUser(userId: String)

  case class FetchedUserDescriptionWordsForUser(userId: String, wordCount: Map[String,Int])

  case class FetchRequestFailed(errorMessage: String)

}
