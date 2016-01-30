package protocol

object TwitterWordCloudServiceActorProtocol {

  case object Initialize

  case class FetchCloudKeywords(users: List[String])

  case object RequestSuccessful

  case class ErrorResponse(errorMessage: String)

}
