package com.benomac.hands

import com.benomac.cards.*
import com.benomac.cards.Suit.*
import com.benomac.cards.Value.*

import scala.annotation.tailrec




object Straight:

  @tailrec
  def isStraight(cards: List[Card]): Boolean =
    (cards.headOption, cards.tail.headOption) match
      case (Some(card1), Some(card2)) if card2.value.score - card1.value.score == 1 =>
        isStraight(cards.tail)
      case (Some(_), None) =>
        true
      case _ => false

