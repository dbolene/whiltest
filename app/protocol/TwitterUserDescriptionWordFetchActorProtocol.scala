package protocol

/**
  *
  */
object TwitterUserDescriptionWordFetchActorProtocol {

  case class FetchUserDescriptionWordsForUser(userId: String)

  case class FetchedUserDescriptionWordsForUser(userId: String, words: List[String])

  case class FetchRequestFailed(errorMessage: String)

}
