package com.benomac.game

import com.benomac.cards.Deck.createDeck
import com.benomac.cards.Rank.*
import com.benomac.cards.Suit.*
import com.benomac.cards.{Card, Deck}
import com.benomac.hands.Hand
import com.benomac.hands.PokerHand.{BestHand, Remaining, RoyalFlush, ThreeOfAKind}

case class Game(player1Hand: Hand, player2Hand: Hand)

object Game {
  
  def dealGame(deck: Deck): Game = {
    val hands = deck.cards.foldLeft(List[Card](), List[Card]())(
      (hands, card) => {
        val (p2Cards, p1Cards) = hands
        (p2Cards.length, p1Cards.length) match
          case (5, 5) => (p2Cards, p1Cards)
          case (p1, p2) if p1 > p2 => (p2Cards, p1Cards :+ card)
          case _ => (p2Cards :+ card, p1Cards)
      }
    )
    Game(Hand.apply(hands._1), Hand(hands._2))
  }

  def main(args: Array[String]): Unit = {
    val game = dealGame(createDeck)
    game.player1Hand.cards.foreach(c => println(s"suit = ${c.suit}, rank = ${c.rank}"))
    println(game.player1Hand.returnBestHand().showBestHand)
    println(game.player2Hand.returnBestHand().showBestHand)

//    val highCard = Hand(
//      List(
//        Card(Ace(14), Clubs),
//        Card(Seven(), Hearts),
//        Card(Three(), Hearts),
//        Card(Five(), Hearts),
//        Card(Six(), Diamonds)
//      )
//    )
//    println(highCard.returnBestHand().showBestHand)
//    println(
//      BestHand(
//        ThreeOfAKind(
//          List(
//            Card(Ten(), Spades),
//            Card(Ten(), Hearts),
//            Card(Ten(), Clubs)
//          )
//        ),
//        Remaining(List(Card(King(), Spades),
//          Card(Ace(14), Spades)))
//      ).showBestHand
//    )
  }
  
}
