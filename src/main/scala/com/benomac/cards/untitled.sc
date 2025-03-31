import com.benomac
import com.benomac.cards.Suit.{Clubs, Diamonds, Hearts, Spades}
import com.benomac.cards.Value.*
import com.benomac.cards.{Card, Hand, Suit, Value}
import com.benomac.hands.WinningHand
import com.benomac.hands.WinningHand.{BestHand, Remaining, TwoPair}

import scala.collection.immutable.HashMap

case class Person(name: String, sex: String)

val persons = List(Person("Thomas", "male"), Person("Sowell", "male"), Person("Liz", "female"))

val foldedList = persons.foldLeft(List[String]()) { (accumulator, person) =>
  val title = person.sex match {
    case "male" => "Mr."
    case "female" => "Ms."
  }
  accumulator :+ s"$title ${person.name}"
}

val hand = Hand(
  List(
    Card(Two(), Spades),
    Card(Three(), Hearts),
    Card(Ace(), Clubs),
    Card(Five(), Diamonds),
    Card(Four(), Spades)
  )
)
hand.makeAcesHigh

def getTwoPairs(cards: List[Card] = hand.cards, winningHand: List[Card] = Nil, remaining: List[Card] = Nil): BestHand =
  val values: List[Int] = hand.cards.map(_.value.score)
  cards match
    case ::(head, next) if values.count(_ == head.value.score) == 2 => getTwoPairs(next, winningHand :+ head, remaining)
    case ::(head, next) if values.count(_ == head.value.score) == 1 => getTwoPairs(next, winningHand , remaining :+ head)
    case _ => BestHand(TwoPair(winningHand), Remaining(remaining))

getTwoPairs()
