package akka.persistence

import akka.persistence.journal.{Tagged => AkkaTagged}

package object pg {

  type EventTag = (String, String)
  object EventTag {
    def apply(tag: String): EventTag = tag match {
      case s"$key:$value" => key -> value
      case value          => ""  -> value
    }
    def apply(tagged: AkkaTagged): Map[String, String] = tagged.tags.map(apply).toMap
  }
}
