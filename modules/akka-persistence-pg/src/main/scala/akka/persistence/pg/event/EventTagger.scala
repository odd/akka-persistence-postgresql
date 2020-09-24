package akka.persistence.pg.event

import akka.persistence.journal.{Tagged => AkkaTagged}
import akka.persistence.pg._

trait EventTagger {

  /**
    * @param event the event/message (argument of persist call)
    * @return the tags and to persist.
    */
  def tags(event: Any): Map[String, String]

}

object NotTagged extends EventTagger {

  override def tags(event: Any) = Map.empty

}

object DefaultTagger extends EventTagger {

  override def tags(event: Any) = event match {
    case t: Tagged     => t.tags
    case t: AkkaTagged => t.tags.map(EventTag.apply).toMap
    case _             => Map.empty
  }

}
