package com.benomac.hands

import com.benomac.cards.Rank.*
import com.benomac.cards.Card
import com.benomac.hands


sealed trait PokerHand: 
  val cards: List[Card]

  def name: String

  def show =
    val shown = for {
      card <- cards
    } yield s"${card.rank.name} of ${card.suit}"
    shown.mkString(", ")

object PokerHand:

  // TODO make getHighCard be able to take any list of card and 
  // Keep returning the highest card if there is a draw, like 
  // both player have a pair of twos
  case class BestHand(winningHand: PokerHand, remainingCards: Remaining) {
    def showBestHand =
      if (remainingCards.cards.isEmpty)
        s"""Your best hand is ${winningHand.name}:
           |${winningHand.show}
           |""".stripMargin
      else
        s"""Your best hand is ${winningHand.name}:
           |${winningHand.show}
           |(${remainingCards.show})
           |""".stripMargin
  }
  
  case class Remaining(cards: List[Card]) extends PokerHand:
    override def name: String = "Remaining Cards"

  case class RoyalFlush(cards: List[Card]) extends PokerHand:
    override def name = "Royal Flush"

  case class StraightFlush(cards: List[Card]) extends PokerHand:
    override def name = "A Straight Flush"

  case class FourOfAKind(cards: List[Card]) extends PokerHand:
    override def name = "Four Of A Kind"

  case class FullHouse(cards: List[Card]) extends PokerHand:
    override def name = "A Full House"

  case class Flush(cards: List[Card]) extends PokerHand:
    override def name = "A Flush"

  case class Straight(cards: List[Card]) extends PokerHand:
    override def name = "A Straight"

  case class ThreeOfAKind(cards: List[Card]) extends PokerHand:
    override def name = "Three Of A Kind"

  case class TwoPair(cards: List[Card]) extends PokerHand:
    override def name = "Two Pair"

  case class Pair(cards: List[Card]) extends PokerHand:
    override def name = "A Pair"

  case class HighCard(cards: List[Card]) extends PokerHand:
    override def name = "A High Card"
  
  
  