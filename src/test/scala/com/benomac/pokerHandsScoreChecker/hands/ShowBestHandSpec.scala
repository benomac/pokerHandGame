package com.benomac.pokerHandsScoreChecker.hands

import org.scalatest.funsuite.AnyFunSuite
import com.benomac.cards.*
import com.benomac.cards.Suit.*
import com.benomac.cards.Rank.*
import com.benomac.hands.WinningHand
import com.benomac.hands.WinningHand.*
import com.benomac.pokerHandsScoreChecker.fixtures.HandsFixtures.*

class ShowBestHandSpec extends AnyFunSuite {

  test("Royal flush should print this") {
    println(royalFlush.returnBestHand().showBestHand)
    true
  }

  
}
