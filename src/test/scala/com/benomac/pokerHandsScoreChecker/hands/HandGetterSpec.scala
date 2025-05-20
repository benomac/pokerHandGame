package com.benomac.pokerHandsScoreChecker.hands

import com.benomac.cards.*
import com.benomac.cards.Suit.*
import com.benomac.cards.Rank.*
import com.benomac.hands.PokerHand
import com.benomac.hands.PokerHand.*
import org.scalatest.funsuite.AnyFunSuite
import com.benomac.pokerHandsScoreChecker.fixtures.HandsFixtures.*

class HandGetterSpec extends AnyFunSuite {

  test("should return a BestHand of RoyalFlush") {
    val expected = BestHand(
      RoyalFlush(
        List(
          Card(Ten(), Spades),
          Card(Jack(), Spades),
          Card(Queen(), Spades),
          Card(King(), Spades),
          Card(Ace(14), Spades)
        )
      ),
      Remaining(List())
    )
    assert(royalFlush.returnBestHand() == expected)
  }

  test("should return a BestHand of StraightFlush") {
    val expected = BestHand(
      StraightFlush(
        List(
          Card(Three(), Spades),
          Card(Four(), Spades),
          Card(Five(), Spades),
          Card(Six(), Spades),
          Card(Seven(), Spades)
        )
      ),
      Remaining(List())
    )
    assert(straightFlush.returnBestHand() == expected)
  }

  test("should return a BestHand of FourOfAKind") {
    val expected = BestHand(
      FourOfAKind(
        List(
          Card(Three(), Spades),
          Card(Three(), Hearts),
          Card(Three(), Clubs),
          Card(Three(), Diamonds)
        )
      ),
      Remaining(List(Card(Four(), Spades)))
    )
    assert(fourOfAKind.returnBestHand() == expected)
  }

  test("should return a BestHand of FullHouse") {
    val expected = BestHand(
      FullHouse(
        List(
          Card(Two(), Diamonds),
          Card(Two(), Spades),
          Card(Three(), Spades),
          Card(Three(), Hearts),
          Card(Three(), Clubs)
        )
      ),
      Remaining(List())
    )
    assert(fullHouse.returnBestHand() == expected)
  }

  test("should return a BestHand of Flush") {
    val expected = BestHand(
      Flush(
        List(
          Card(Three(), Spades),
          Card(Four(), Spades),
          Card(Five(), Spades),
          Card(Seven(), Spades),
          Card(King(), Spades)
        )
      ),
      Remaining(List())
    )
    assert(flush.returnBestHand() == expected)
  }

  test("should return a BestHand of Straight") {
    val expected = BestHand(
      Straight(
        List(
          Card(Three(), Spades),
          Card(Four(), Hearts),
          Card(Five(), Diamonds),
          Card(Six(), Clubs),
          Card(Seven(), Spades)
        )
      ),
      Remaining(List())
    )
    assert(straight.returnBestHand() == expected)
  }

  test("should return a BestHand of ThreeOfAKind") {
    val expected = BestHand(
      ThreeOfAKind(
        List(
          Card(Three(), Spades),
          Card(Three(), Hearts),
          Card(Three(), Clubs)
        )
      ),
      Remaining(List(Card(Four(), Spades), Card(Five(), Diamonds)))
    )
    assert(threeOfAKind.returnBestHand() == expected)
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
    assert(twoPair.returnBestHand() == expected)
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
    assert(pair.returnBestHand() == expected)
  }

  test("should return a BestHand of HighCard") {
    val expected = BestHand(
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
    assert(highCardOnly.returnBestHand() == expected)
  }
}
