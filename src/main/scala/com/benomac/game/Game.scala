package com.benomac.game

import com.benomac.cards.Deck.createDeck
import com.benomac.cards.Rank.{Ace, Jack, King, Queen, Ten}
import com.benomac.cards.Suit.*
import com.benomac.cards.{Card, Deck}
import com.benomac.hands.Hand
import com.benomac.hands.WinningHand.{BestHand, Remaining, RoyalFlush, ThreeOfAKind}

case class Game(player1: Hand, player2: Hand)

object Game {
  
  def dealGame(deck: Deck): Game = {
    println(deck)
    val hands = deck.cards.foldLeft(List[Card](), List[Card]())(
      (game, card) => {
        val player1Cards: List[Card] = game._1
        val player2Cards: List[Card] = game._2
        (player1Cards.length, player2Cards.length) match
          case (5, 5) => (player1Cards, player2Cards)
          case (p1, p2) if p1 > p2 => (player1Cards, player2Cards :+ card)
          case _ => (player1Cards :+ card, player2Cards)
      }
    )
    Game(Hand(hands._1), Hand(hands._2))
  }

  def main(args: Array[String]): Unit = {
    val game = dealGame(createDeck.shuffleDeck)
    println(game.player1.returnBestHand().showBestHand)
    println(game.player2.returnBestHand().showBestHand)
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
