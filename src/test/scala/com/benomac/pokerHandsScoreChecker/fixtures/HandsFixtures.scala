package com.benomac.pokerHandsScoreChecker.fixtures

import com.benomac.cards.Hand
import com.benomac.cards.*
import com.benomac.cards.Suit.*
import com.benomac.cards.Value.*


object HandsFixtures:

  val isUnOrderedStraight: Hand = Hand(List(
    Card(Three(), Spades),
    Card(Seven(), Spades),
    Card(Five(), Spades),
    Card(Six(), Spades),
    Card(Four(), Spades))
  )
