package com.benomac.pokerHandsScoreChecker.hands

import com.benomac.cards.*
import com.benomac.cards.Suit.*
import com.benomac.cards.Rank.*
import com.benomac.hands.PokerHand
import com.benomac.hands.PokerHand.*
import org.scalatest.funsuite.AnyFunSuite
import com.benomac.pokerHandsScoreChecker.fixtures.HandsFixtures.*

class HandleAcesInHandsSpec extends AnyFunSuite {

  test("value of ace should be 1") {
    val testDeck = Deck(
      List(
        Card(Ace(), Hearts),
        Card(Ace(), Spades),
        Card(Ace(), Spades),
        Card(Ace(), Spades),
        Card(Ace(), Spades)
      )
    )
  }
}
