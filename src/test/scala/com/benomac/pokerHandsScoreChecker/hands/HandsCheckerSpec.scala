package com.benomac.pokerHandsScoreChecker.hands

import com.benomac.cards.*
import com.benomac.cards.Suit.*
import com.benomac.cards.Value.*
import com.benomac.hands.WinningHands
import com.benomac.hands.WinningHands.{
  BestHand,
  FourOfAKind,
  Pair,
  Remaining,
  ThreeOfAKind,
  TwoPair
}
import org.scalatest.funsuite.AnyFunSuite
import com.benomac.pokerHandsScoreChecker.fixtures.HandsFixtures.*

class HandsCheckerSpec extends AnyFunSuite {

  test("hands should be sorted") {
    val expected1 = List(3, 4, 5, 6, 7)
    val expected2 = List(3, 4, 5, 7, 13)
    assert(isAStraightHand.cards.map(_.value.score) == expected1)
    assert(isNotAStraightHand.cards.map(_.value.score) == expected2)
  }

  test("hands containing duplicates should throw an exception") {
    assertThrows[Exception](
      Hand(
        List(
          Card(Three(), Spades),
          Card(Seven(), Spades),
          Card(Five(), Spades),
          Card(King(), Spades),
          Card(King(), Spades)
        )
      )
    )
  }

  test("hands with too few cards should throw an exception") {
    assertThrows[Exception](
      Hand(
        List(
          Card(Three(), Spades),
          Card(Seven(), Spades),
          Card(Five(), Spades),
          Card(King(), Spades)
        )
      )
    )
  }

  test("hands with too many cards should throw an exception") {
    assertThrows[Exception](
      Hand(
        List(
          Card(Three(), Spades),
          Card(Seven(), Spades),
          Card(Five(), Spades),
          Card(King(), Spades),
          Card(Queen(), Hearts),
          Card(Jack(), Hearts)
        )
      )
    )
  }

  test(
    "hands with too many cards due to duplicates should throw an exception"
  ) {
    assertThrows[Exception](
      Hand(
        List(
          Card(Three(), Spades),
          Card(Seven(), Spades),
          Card(Five(), Spades),
          Card(Queen(), Hearts),
          Card(Queen(), Hearts),
          Card(Queen(), Hearts),
          Card(Jack(), Spades)
        )
      )
    )
  }

  test("isStraight should be true") {
    assert(isAStraightHand.isStraight())
  }

  test("isStraight should be false") {
    assert(!isNotAStraightHand.isStraight())
  }

  test("royalFlush should be a straight") {
    assert(royalFlush.isStraight())
  }

  test("royalFlush should be a flush") {
    assert(royalFlush.checkForRoyalStraight)
  }

  test("aceLowStraight should be a straight") {
    assert(aceLowStraightFlush.isStraight())
  }

  test("aceLowStraightFlush score should be 29") {
    assert(aceLowStraightFlush.getScoreForHand == 29)
  }

  test("aceLowStraight score should be 28") {
    assert(aceLowStraight.getScoreForHand == 28)
  }

  test("should be four of a kind") {
    assert(fourOfAKind.isFourOfAKind)
  }

  test("should return the correct best hand four of a kind") {
    val expected =
      BestHand(
        FourOfAKind(
          List(
            Card(Three(), Spades),
            Card(Three(), Hearts),
            Card(Three(), Clubs),
            Card(Three(), Diamonds)
          )
        ),
        Remaining(
          List(
            Card(Four(), Spades)
          )
        )
      )
    assert(fourOfAKind.getThreeOrFourOfAKind == expected)
  }

  test("should be three of a kind") {
    assert(threeOfAKind.isThreeOfAKind)
  }

  test("should return the correct best hand three of a kind") {
    val expected =
      BestHand(
        ThreeOfAKind(
          List(
            Card(Three(), Spades),
            Card(Three(), Hearts),
            Card(Three(), Clubs)
          )
        ),
        Remaining(
          List(
            Card(Four(), Spades),
            Card(Five(), Diamonds)
          )
        )
      )
    assert(threeOfAKind.getThreeOrFourOfAKind == expected)
  }

  test("is full house") {
    assert(fullHouse.isFullHouse)
  }

  test("is not three of a kind") {
    assert(!fullHouse.isThreeOfAKind)
  }

  test("is a pair") {
    assert(pair.isPair)
  }

  test("is not a pair") {
    assert(!twoPair.isPair)
  }

  test("is two pair") {
    assert(twoPair.isTwoPair)
  }

  test("is not two pair") {
    assert(!pair.isTwoPair)
  }

  test("isHighCard should be true") {
    assert(highCardOnly.isHighCard)
  }

  test("should return the highest card") {
    val expected = Card(Seven(), Spades)
    assert(highCardOnly.getHighCard == expected)
  }

  test("should return a BestHand of TwoPair") {
    val expected = BestHand(
      TwoPair(
        List(
          Card(Three(), Spades),
          Card(Three(), Hearts),
          Card(Four(), Clubs),
          Card(Four(), Spades)
        )
      ),
      Remaining(List(Card(Five(), Diamonds)))
    )
    assert(twoPair.getPairOrTwoPairs == expected)
  }

  test("should return a BestHand of Pair") {
    val expected = BestHand(
      Pair(
        List(
          Card(Three(), Spades),
          Card(Three(), Hearts)
        )
      ),
      Remaining(
        List(
          Card(Four(), Clubs),
          Card(Five(), Diamonds),
          Card(Seven(), Spades)
        )
      )
    )
    assert(pair.getPairOrTwoPairs == expected)
  }

}
