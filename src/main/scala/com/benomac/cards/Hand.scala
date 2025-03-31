package com.benomac.cards

import com.benomac.cards.Card
import com.benomac.cards.Value.*
import com.benomac.cards.Suit.*
import com.benomac.hands.WinningHands
import com.benomac.hands.WinningHands.{BestHand, Flush, FourOfAKind, FullHouse, HighCard, Pair, Remaining, RoyalFlush, Straight, StraightFlush, ThreeOfAKind, TwoPair}

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

  private def valueCount(card: Card, values: List[Int], amount: Int): Boolean =
    values.count(_ == card.value.score) == amount

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
  private final def threeOrFourHelper(hand: List[Card] = cards, winningHand: List[Card] = Nil, remaining: List[Card] = Nil): BestHand = {
    val values: List[Int] = cards.map(_.value.score)
    hand match
      case ::(head, next) if valueCount(head, values, 3) => threeOrFourHelper(next, winningHand :+ head, remaining)
      case ::(head, next) if valueCount(head, values, 4) => threeOrFourHelper(next, winningHand :+ head, remaining)
      case ::(head, next) if valueCount(head, values, 1) => threeOrFourHelper(next, winningHand, remaining :+ head)
      case _ => winningHand.length match
        case 4 => BestHand(FourOfAKind(winningHand), Remaining(remaining)) //may need to sort remaining
        case 3 => BestHand(ThreeOfAKind(winningHand), Remaining(remaining))
  }
  def getThreeOrFourOfAKind: BestHand =
    if (isThreeOfAKind || isFourOfAKind)
      threeOrFourHelper()
    else
      throw new Exception("This is not three or four of a kind")


  def getFullHouse: BestHand =
    if (isFullHouse) BestHand(FullHouse(cards), Remaining(Nil))
    else
      throw new Exception("This is not a full house")

  def getFlush: BestHand =
    if (isFlush) BestHand(Flush(cards), Remaining(Nil))
    else
      throw new Exception("This is not a flush")

  def getStraight: BestHand =
    if (isStraight()) BestHand(Straight(cards), Remaining(Nil))
    else
      throw new Exception("This is not a straight")

  def getPairOrTwoPairs: BestHand =
    if (isTwoPair || isPair) {
      @tailrec
      def helper(hand: List[Card] = cards, winningHand: List[Card] = Nil, remaining: List[Card] = Nil): BestHand = {
        val values: List[Int] = cards.map(_.value.score)
        hand match
          case ::(head, next) if valueCount(head, values, 2) => helper(next, winningHand :+ head, remaining)
          case ::(head, next) if valueCount(head, values, 1) => helper(next, winningHand, remaining :+ head)
          case _ => winningHand.length match
            case 4 => BestHand(TwoPair(winningHand), Remaining(remaining)) //may need to sort remaining
            case 2 => BestHand(Pair(winningHand), Remaining(remaining))
      }
      helper()
    } else
      throw new Exception("There are no pairs in this hand")

  def getHighCard: BestHand =
    if isHighCard then
      BestHand(HighCard(makeAcesHigh.maxBy(_.value.score)), Remaining(Nil))
    else
      throw new Exception("There is a better hand than high card.")


  def returnBestHand(
      isRoyalFlush: Boolean,
      isStraightFlush: Boolean,
      isFourOfAkind: Boolean,
      isFullHouse: Boolean,
      isFlush: Boolean,
      isStraight: Boolean,
      isThreeOfAkind: Boolean,
      isTwoPair: Boolean,
      isPair: Boolean,
      isHighCard: Boolean
                          ): BestHand =
    (isRoyalFlush,
      isStraightFlush,
      isFourOfAkind,
      isFullHouse,
      isFlush,
      isStraight,
      isThreeOfAkind,
      isTwoPair,
      isPair,
      isHighCard) match {
      case (true, _, _, _, _, _, _, _, _, _) => getRoyalFlush
      case (_, true, _, _, _, _, _, _, _, _) => getStraightFlush
      case (_, _, true, _, _, _, _, _, _, _) => getThreeOrFourOfAKind
      case (_, _, _, true, _, _, _, _, _, _) => getFullHouse
      case (_, _, _, _, true, _, _, _, _, _) => getFlush
      case (_, _, _, _, _, true, _, _, _, _) => getStraight
      case (_, _, _, _, _, _, true, _, _, _) => getThreeOrFourOfAKind
      case (_, _, _, _, _, _, _, true, _, _) => getPairOrTwoPairs
      case (_, _, _, _, _, _, _, _, true, _) => getPairOrTwoPairs
      case (_, _, _, _, _, _, _, _, _, true) => getHighCard
      case _ => ???
    }
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
