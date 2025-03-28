package com.benomac.cards

import com.benomac.cards.Card
import com.benomac.cards.Value.*
import com.benomac.cards.Suit.*
import com.benomac.hands.WinningHands
import com.benomac.hands.WinningHands.{BestHand, FourOfAKind, Remaining, RoyalFlush, StraightFlush, ThreeOfAKind}

import scala.annotation.tailrec

case class Hand(cards: List[Card]) {

  def makeAcesHigh: List[Card] = cards.map {
    card =>
      if (card.value == Ace()) Card(Ace(14), card.suit)
      else card
  }

  // TODO this is not finished
  def getScoreForHand: Int =
    val initialScore = makeAcesHigh.map(_.value.score).sum
    if (cards.map(_.suit).toSet.size == 1 && isStraight(cards))
      println("bonus")
      initialScore + 1 // may need to change the bonus score increase.
    else
      println("no bonus")
      initialScore

  def valueMap: Map[Value, Int] = cards.groupBy(_.value).map { case (suit, cards) =>
    suit -> cards.length
  }

  def checkForRoyalStraight: Boolean =
    cards.map(_.value) == List(Ten(), Jack(), Queen(), King(), Ace(14))
  
  // winning hands checkers
  def isRoyalFlush: Boolean =
    checkForRoyalStraight &&
      isStraight(cards) &&
      isFlush

  def isStraightFlush: Boolean =
    !checkForRoyalStraight &&
      isStraight(cards) &&
      isFlush

  def isFourOfAKind: Boolean =
    valueMap.values.toList.contains(4)

  
  def isFullHouse: Boolean =
    valueMap.values.toList.contains(3) &&
      valueMap.values.toList.contains(2)

  def isFlush: Boolean =
    if (cards.map(_.suit).toSet.size == 1 && isStraight(cards))
      true
    else
      false

  @tailrec
  final def isStraight(hand: List[Card] = cards): Boolean =
    (hand.headOption, hand.tail.headOption) match
      case (Some(card1), Some(card2)) if card2.value.score - card1.value.score == 1 =>
        isStraight(hand.tail)
      case (Some(_), None) =>
        true
      case _ => false


  def isThreeOfAKind: Boolean =
    valueMap.values.toList.contains(3) &&
      !valueMap.values.toList.contains(2)

  def isTwoPair: Boolean =
    valueMap.values.toList.count(_ == 2) == 2

  def isPair: Boolean =
    valueMap.values.toList.count(_ == 2) == 1
    
  def isHighCard: Boolean =
    !isRoyalFlush &&
      !isStraightFlush &&
      !isFourOfAKind &&
      !isFullHouse &&
      !isFlush &&
      !isStraight()
    !isThreeOfAKind &&
      !isTwoPair &&
      !isPair

  def getRoyalFlush: BestHand =
    BestHand(RoyalFlush(cards), Remaining(Nil))

  def getStraightFlush: BestHand =
    BestHand(StraightFlush(cards), Remaining(Nil))

  @tailrec
    //used to retrieve the BestHand for 3 or 4 of a kind
  final def getThreeOrFourOfAKind(hand: List[Card] = cards, winningHand: List[Card] = Nil, remain: List[Card] = Nil): BestHand =
    val cardToGet: Value = valueMap.maxBy(_._2)._1
    hand match
      case ::(head, next) if head.value == cardToGet => getThreeOrFourOfAKind(next, winningHand :+ head, remain)
      case ::(head, next) => getThreeOrFourOfAKind(next, winningHand, remain :+ head)
      case Nil => winningHand.length match
        case 4 => BestHand(FourOfAKind(winningHand), Remaining(remain))
        case 3 => BestHand(ThreeOfAKind(winningHand), Remaining(remain))

  def getHighCard: Card =
    !isRoyalFlush &&
      !isStraightFlush &&
      !isFourOfAKind &&
      !isFullHouse &&
      !isFlush &&
      !isStraight()
    !isThreeOfAKind &&
      !isTwoPair &&
      !isPair match
      case true => makeAcesHigh.maxBy(_.value.score)
      case _ => throw new Exception("There is a better hand than high card.")


//  def returnBestHand(
//      isRoyalFlush: Boolean,
//      isStraightFlush: Boolean,
//      isFourOfAkind: Boolean,
//      isFullHouse: Boolean,
//      isFlush: Boolean,
//      isStraight: Boolean,
//      isThreeOfAkind: Boolean,
//      isTwoPair: Boolean,
//      isPair: Boolean,
//      getHighCard: Boolean
//                          ): BestHand =
//
//    (isRoyalFlush,
//      isStraightFlush,
//      isFourOfAkind,
//      isFullHouse,
//      isFlush,
//      isStraight,
//      isThreeOfAkind,
//      isTwoPair,
//      isPair,
//      getHighCard) match {
//      case (true, _, _, _, _, _, _, _, _, _) => BestHand()
//      case (_, true, _, _, _, _, _, _, _, _) => 
//      case (_, _, true, _, _, _, _, _, _, _) => 
//      case (_, _, _, true, _, _, _, _, _, _) => 
//      case (_, _, _, _, true, _, _, _, _, _) => 
//      case (_, _, _, _, _, true, _, _, _, _) => 
//      case (_, _, _, _, _, _, true, _, _, _) => 
//      case (_, _, _, _, _, _, _, true, _, _) => 
//      case (_, _, _, _, _, _, _, _, true, _) => 
//      case (_, _, _, _, _, _, _, _, _, true) => 
//    }
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
    // so they should always be high as apposed to the inverse. Going to leave as low for now
    // as it's needed for sorting during dealing.
    def aceChecked(cards: List[Card] = cards): List[Card] =
      if (cards.map(_.value).contains(Ace()) && cards.map(_.value).contains(King())) {
        val cardsEdited: List[Card] = cards.map {
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
