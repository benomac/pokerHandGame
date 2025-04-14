package com.benomac.hands

import com.benomac.cards.Rank.*
import com.benomac.cards.Card
import com.benomac.hands


sealed trait WinningHand: 
  val cards: List[Card]

  def name: String
  
  def show =
    val shown = for {
      card <- cards
    } yield s"${card.rank.name} of ${card.suit}"
    shown.mkString(", ")
  
object WinningHand:

  // TODO make getHighCard be able to take any list of card and 
  // Keep returning the highest card if there is a draw, like 
  // both player have a pair of twos
  case class BestHand(winningHand: WinningHand, remainingCards: Remaining) {
    def showBestHand =
      if (remainingCards.cards.isEmpty) 
        s"Your Best hand is ${winningHand.name}\n${winningHand.show}\n"
      else
        s"Your Best hand is ${winningHand.name}\n${winningHand.show} \n${remainingCards.show}\n"
  }
  
  case class Remaining(cards: List[Card]):
    def show: String =
      val shown = for {
        card <- cards
      } yield s"${card.rank.name} of ${card.suit}"
      shown.mkString(", ") 
  
  case class RoyalFlush(cards: List[Card]) extends WinningHand:
    override def name = "Royal Flush"
  
  case class StraightFlush(cards: List[Card]) extends WinningHand:
    override def name = "A Straight Flush"
    
  case class FourOfAKind(cards: List[Card]) extends WinningHand:
    override def name = "Four Of A Kind"
  
  case class FullHouse(cards: List[Card]) extends WinningHand:
    override def name = "A Full House"
  
  case class Flush(cards: List[Card]) extends WinningHand:
    override def name = "A Flush"
  
  case class Straight(cards: List[Card]) extends WinningHand:
    override def name = "A Straight"
  
  case class ThreeOfAKind(cards: List[Card]) extends WinningHand:
    override def name = "Three Of A Kind"
  
  case class TwoPair(cards: List[Card]) extends WinningHand:
    override def name = "Two Pair"
  
  case class Pair(cards: List[Card]) extends WinningHand:
    override def name = "A Pair"
  
  case class HighCard(cards: List[Card]) extends WinningHand:
    override def name = "A High Card"
  
  
  