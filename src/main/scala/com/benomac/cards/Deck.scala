package com.benomac.cards

import com.benomac.cards.Rank.ranks
import com.benomac.cards.Suit.suits

case class Deck (cards: List[Card])

object Deck {
  def createDeck: Deck =
    Deck(for {
      rank <- ranks
      suit <- suits
    } yield Card(rank, suit))



  def main(args: Array[String]): Unit = {
    println(createDeck.cards.length)
  }
}
