package akka.persistence

package object pg {
  type EventTag = (String, String)
  object EventTag {
    def apply(tag: String): EventTag = tag match {
      case s"$key:$value" => key -> value
      case key            => key -> null
    }
  }
}
