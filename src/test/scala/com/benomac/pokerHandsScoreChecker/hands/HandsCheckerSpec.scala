package com.benomac.pokerHandsScoreChecker.hands

import com.benomac.cards.*
import com.benomac.cards.Suit.*
import com.benomac.cards.Value.*
import com.benomac.hands.WinningHand
import com.benomac.hands.WinningHand.{
  BestHand,
  FourOfAKind,
  HighCard,
  Pair,
  Remaining,
  ThreeOfAKind,
  TwoPair
}
import org.scalatest.funsuite.AnyFunSuite
import com.benomac.pokerHandsScoreChecker.fixtures.HandsFixtures.*

class HandsCheckerSpec extends AnyFunSuite {

  test("isStraight should be true") {
    assert(straightFlush.isStraight())
  }

  test("isStraight should be false") {
    assert(!flush.isStraight())
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
    assert(fourOfAKind.getFourOfAKind == expected)
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
    assert(threeOfAKind.getThreeOfAKind == expected)
  }

  test("is full house") {
    assert(fullHouse.isFullHouse)
  }

  test("full house is not three of a kind") {
    assert(!fullHouse.isThreeOfAKind)
  }

  test("full house is not a pair") {
    assert(!fullHouse.isPair)
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

  test("four of a kind is not two pair") {
    assert(!fourOfAKind.isTwoPair)
  }

  test("isHighCard should be true") {
    assert(highCardOnly.isHighCard)
  }

  test("should return the highest card") {
    val expected =
      BestHand(
        HighCard(List(Card(Seven(), Spades))),
        Remaining(
          List(
            Card(Two(), Spades),
            Card(Four(), Spades),
            Card(Five(), Spades),
            Card(Six(), Hearts)
          )
        )
      )
    assert(highCardOnly.getHighCard == expected)
  }

}
