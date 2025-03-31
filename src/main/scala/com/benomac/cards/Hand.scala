package com.benomac.cards

import com.benomac.cards.Card
import com.benomac.cards.Value.*
import com.benomac.cards.Suit.*
import com.benomac.hands.WinningHand
import com.benomac.hands.WinningHand.{BestHand, Flush, FourOfAKind, FullHouse, HighCard, Pair, Remaining, RoyalFlush, Straight, StraightFlush, ThreeOfAKind, TwoPair}

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

  private def getMultiplesHands(multiple: Int)
                               (emptyWinningHand: () => WinningHand,
                                addWinningCard: List[Card] => WinningHand,
                                addRemainingCard: List[Card] => Remaining): BestHand =
    val values: List[Value] = cards.map(_.value)
    cards.foldLeft(BestHand(emptyWinningHand(), Remaining(Nil)))((bh, card) => {
      if (containsAmountOfValues(card = card, values = values, amount = multiple))
        BestHand(addWinningCard(bh.winningHand.cards :+ card), bh.remainingCards)
      else
        BestHand(bh.winningHand, addRemainingCard(bh.remainingCards.cards :+ card))
    })

  private def containsAmountOfValues(card: Card, values: List[Value], amount: Int): Boolean =
    values.count(_ == card.value) == amount

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
    if (cards.map(_.suit).toSet.size == 1)
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
    valueMap.values.toList.count(_ == 2) == 1 &&
      valueMap.values.toList.count(_ == 3) != 1

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

  def getFourOfAKind: BestHand =
    if (!isFourOfAKind) throw new Exception("Not four of a kind")
    else
      this.getMultiplesHands(multiple = 4)(
        () => FourOfAKind(Nil),
        cards => FourOfAKind(cards),
        cards => Remaining(cards)
      )

  def getThreeOfAKind: BestHand =
    if (!isThreeOfAKind) throw new Exception("Not three of a kind")
    else
      this.getMultiplesHands(multiple = 3)(
        () => ThreeOfAKind(Nil),
        cards => ThreeOfAKind(cards),
        cards => Remaining(cards)
      )

  def getPair: BestHand =
    if (!isPair) throw new Exception("Not a pair")
    else
      this.getMultiplesHands(multiple = 2)(
        () => Pair(Nil),
        cards => Pair(cards),
        cards => Remaining(cards)
      )

  def getTwoPair: BestHand =
    if (!isTwoPair) throw new Exception("Not Two pair")
    else
      this.getMultiplesHands(multiple = 2)(
        () => TwoPair(Nil),
        cards => TwoPair(cards),
        cards => Remaining(cards)
      )


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

  def getHighCard: BestHand =
    if (isHighCard)
      val highCard: Card = makeAcesHigh.maxBy(_.value.score)
      cards.foldLeft(BestHand(HighCard(Nil), Remaining(Nil))) {
        (bh, card) =>
          if (card == highCard)
            BestHand(HighCard(bh.winningHand.cards :+ card), bh.remainingCards)
          else
            BestHand(bh.winningHand, Remaining(bh.remainingCards.cards :+ card))
      }
    else
      throw new Exception("There is a better hand than high card.")


  def returnBestHand(
      isRoyalFlush: Boolean = isRoyalFlush,
      isStraightFlush: Boolean = isStraightFlush,
      isFourOfAkind: Boolean = isFourOfAKind,
      isFullHouse: Boolean = isFullHouse,
      isFlush: Boolean = isFlush,
      isStraight: Boolean = isStraight(),
      isThreeOfAkind: Boolean = isThreeOfAKind,
      isTwoPair: Boolean = isTwoPair,
      isPair: Boolean = isPair,
      isHighCard: Boolean = isHighCard
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
      case (_, _, true, _, _, _, _, _, _, _) => getFourOfAKind
      case (_, _, _, true, _, _, _, _, _, _) => getFullHouse
      case (_, _, _, _, true, _, _, _, _, _) => getFlush
      case (_, _, _, _, _, true, _, _, _, _) => getStraight
      case (_, _, _, _, _, _, true, _, _, _) => getThreeOfAKind
      case (_, _, _, _, _, _, _, true, _, _) => getTwoPair
      case (_, _, _, _, _, _, _, _, true, _) => getPair
      case (_, _, _, _, _, _, _, _, _, true) => getHighCard
      case _ => throw new Exception("Uh-oh! something's really gone wrong if you're seeing this message!")
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
