package com.benomac.pokerHandsScoreChecker.hands

import org.scalatest.funsuite.AnyFunSuite
import com.benomac.cards.*
import com.benomac.cards.Suit.*
import com.benomac.cards.Rank.*
import com.benomac.hands.PokerHand
import com.benomac.hands.PokerHand.*
import com.benomac.pokerHandsScoreChecker.fixtures.HandsFixtures.*

class ShowBestHandSpec extends AnyFunSuite {

  test("Royal flush should print this") {
    val expected = """Your best hand is Royal Flush:
                     |Ten of Spades, Jack of Spades, Queen of Spades, King of Spades, Ace of Spades
                     |""".stripMargin
    assert(royalFlush.returnBestHand().showBestHand == expected)
  }

  
}
