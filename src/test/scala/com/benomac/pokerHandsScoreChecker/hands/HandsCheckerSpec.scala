package com.benomac.pokerHandsScoreChecker.hands

import com.benomac.cards.*
import com.benomac.cards.Suit.*
import com.benomac.cards.Value.*
import com.benomac.hands.Straight.*
import org.scalatest.funsuite.AnyFunSuite

class HandsCheckerSpec extends AnyFunSuite {

  val isAStraightHand: Hand = Hand(
    List(
      Card(Three(), Spades),
      Card(Seven(), Spades),
      Card(Five(), Spades),
      Card(Six(), Spades),
      Card(Four(), Spades)
    )
  )

  val isNotAStraightHand: Hand = Hand(
    List(
      Card(Three(), Spades),
      Card(Seven(), Spades),
      Card(Five(), Spades),
      Card(King(), Spades),
      Card(Four(), Spades)
    )
  )

  val aceHighStraight: Hand = Hand(
    List(
      Card(Ten(), Spades),
      Card(Jack(), Spades),
      Card(Ace(), Spades),
      Card(Queen(), Spades),
      Card(King(), Spades)

    )
  )

  val aceLowStraight: Hand = Hand(
    List(
      Card(Three(), Spades),
      Card(Ace(), Spades),
      Card(Five(), Spades),
      Card(Two(), Spades),
      Card(Four(), Spades)

    )
  )

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

  test("hands with too many cards due to duplicates should throw an exception") {
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

  test("aceHighStraight should be a straight") {
    assert(aceHighStraight.isStraight())
  }

  test("aceLowStraight should be a straight") {
    assert(aceLowStraight.isStraight())
  }
}
