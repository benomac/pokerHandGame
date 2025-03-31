package com.benomac.pokerHandsScoreChecker.hands

import com.benomac.cards.*
import com.benomac.cards.Suit.*
import com.benomac.cards.Value.*
import com.benomac.hands.WinningHand
import com.benomac.hands.WinningHand.{
  BestHand,
  FourOfAKind,
  Pair,
  Remaining,
  ThreeOfAKind,
  TwoPair
}
import org.scalatest.funsuite.AnyFunSuite
import com.benomac.pokerHandsScoreChecker.fixtures.HandsFixtures.*


class CreatingHandsSpec extends AnyFunSuite:
  test("hands should be sorted") {
    val expected1 = List(3, 4, 5, 6, 7)
    val expected2 = List(3, 4, 5, 7, 13)
    assert(straightFlush.cards.map(_.value.score) == expected1)
    assert(flush.cards.map(_.value.score) == expected2)
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