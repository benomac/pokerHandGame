package com.benomac.pokerHandsScoreChecker.hands

import com.benomac.cards.*
import com.benomac.cards.Suit.*
import com.benomac.cards.Rank.*
import com.benomac.hands.PokerHand.*
import org.scalatest.funsuite.AnyFunSuite
import com.benomac.pokerHandsScoreChecker.fixtures.HandsFixtures.*


// TODO can probably delete this spec as HandGetterSpec cover all the "isX" methods
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
    assert(royalFlush.isFlush)
  }

  test("royalFlush should be a royal flush") {
    assert(royalFlush.isRoyalFlush)
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

  test("should be three of a kind") {
    assert(threeOfAKind.isThreeOfAKind)
  }

  test("three of a kind should not be a pair") {
    assert(!threeOfAKind.isPair)
  }

  test("four of a kind should not be a pair") {
    assert(!fourOfAKind.isPair)
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

  

}
