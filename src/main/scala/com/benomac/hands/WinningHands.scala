package com.benomac.hands

import com.benomac.cards.Value.*
import com.benomac.cards.Suit.*
import com.benomac.cards.Value.*
import com.benomac.cards.{Card, Hand}
import com.benomac.hands

trait WinningHand

object WinningHands:

  case class BestHand(winningHand: WinningHand, remainingCards: Remaining)
  
  case class Remaining(cards: List[Card])
  
  case class RoyalFlush(cards: List[Card]) extends WinningHand
  
  case class StraightFlush(cards: List[Card]) extends WinningHand
  
  case class FourOfAKind(cards: List[Card]) extends WinningHand
  
  case class FullHouse(cards: List[Card]) extends WinningHand
  
  case class Flush(cards: List[Card]) extends WinningHand
  
  case class Straight(cards: List[Card]) extends WinningHand
  
  case class ThreeOfAKind(cards: List[Card]) extends WinningHand
  
  case class TwoPair(cards: List[Card]) extends WinningHand
  
  case class Pair(cards: List[Card]) extends WinningHand
  
  case class HighCard(cards: Card) extends WinningHand
  
  
  
  
  
  