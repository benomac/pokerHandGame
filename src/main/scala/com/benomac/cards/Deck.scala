package com.benomac.cards

import com.benomac.cards.Rank.ranks
import com.benomac.cards.Suit.suits
import scala.util.Random

case class Deck(cards: List[Card]) {

  def shuffleDeck: Deck = {
    Deck(Random.shuffle(cards))
  }

}

object Deck {

  def createDeck: Deck =
    Deck(for {
      rank <- ranks
      suit <- suits
    } yield Card(rank, suit))

  def main(args: Array[String]): Unit = {
    println(createDeck.cards.length)
    val newDeck = createDeck.shuffleDeck
    println(newDeck)

  }
}
