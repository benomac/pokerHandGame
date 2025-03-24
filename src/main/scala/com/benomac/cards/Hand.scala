package com.benomac.cards

import com.benomac.cards.Card
import com.benomac.cards.Value.*
import com.benomac.cards.Suit.*

import scala.annotation.tailrec

case class Hand(cards: List[Card]) {

  @tailrec
  final def isStraight(card: List[Card] = cards): Boolean =
    (card.headOption, card.tail.headOption) match
      case (Some(card1), Some(card2)) if card2.value.score - card1.value.score == 1 =>
        isStraight(card.tail)
      case (Some(_), None) =>
        true
      case _ => false
}

object Hand:

  private def sortHand(hand: List[Card]): List[Card] =
    hand.sorted { (x: Card, y: Card) => x.value.score - y.value.score }


  def apply(cards: List[Card]): Hand = {
    val validHand: Unit =
      if (cards.toSet.size != 5 || cards.length != 5)
        throw new Exception(s"Invalid Hand, you have duplicates or wrong number of cards, 5 expected. $cards")
      else ()

    // this may need more work, I think aces should only be low when they are in a 1 - 5 straight????
    // so they should always be high as apposed to the inverse.
    def aceChecked(cards: List[Card] = cards): List[Card] =
      if (cards.map(_.value).contains(Ace()) && cards.map(_.value).contains(King())) {
        val cardsEdited: List[Card] = cards.map {
          println(1)
          c =>
            if (c.value == Ace()) Card(Ace(14), c.suit)
            else c
        }
        cardsEdited
      }
      else {
        cards
      }
    val aceHighOrLow = aceChecked(cards)
    val sortedCards = sortHand(aceHighOrLow)

    new Hand(sortedCards)
  }
