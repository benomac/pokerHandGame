package com.benomac.pokerHandsScoreChecker.fixtures

import com.benomac.cards.Hand
import com.benomac.cards.*
import com.benomac.cards.Suit.*
import com.benomac.cards.Value.*


object HandsFixtures:

  val royalFlush: Hand = Hand(
    List(
      Card(Ten(), Spades),
      Card(Jack(), Spades),
      Card(Ace(), Spades),
      Card(Queen(), Spades),
      Card(King(), Spades)

    )
  )

  val straightFlush: Hand = Hand(
    List(
      Card(Three(), Spades),
      Card(Seven(), Spades),
      Card(Five(), Spades),
      Card(Six(), Spades),
      Card(Four(), Spades)
    )
  )

  val fourOfAKind: Hand = Hand(
    List(
      Card(Three(), Spades),
      Card(Three(), Hearts),
      Card(Three(), Clubs),
      Card(Three(), Diamonds),
      Card(Four(), Spades)

    )
  )

  val fullHouse: Hand = Hand(
    List(
      Card(Three(), Spades),
      Card(Three(), Hearts),
      Card(Three(), Clubs),
      Card(Two(), Diamonds),
      Card(Two(), Spades)
    )
  )

  val flush: Hand = Hand(
    List(
      Card(Three(), Spades),
      Card(Seven(), Spades),
      Card(Five(), Spades),
      Card(King(), Spades),
      Card(Four(), Spades)
    )
  )

  val straight: Hand = Hand(
    List(
      Card(Three(), Spades),
      Card(Four(), Hearts),
      Card(Six(), Clubs),
      Card(Five(), Diamonds),
      Card(Seven(), Spades)
    )
  )

  val threeOfAKind: Hand = Hand(
    List(
      Card(Three(), Spades),
      Card(Three(), Hearts),
      Card(Three(), Clubs),
      Card(Five(), Diamonds),
      Card(Four(), Spades)
    )
  )

  val twoPair: Hand = Hand(
    List(
      Card(Three(), Spades),
      Card(Three(), Hearts),
      Card(Four(), Clubs),
      Card(Five(), Diamonds),
      Card(Four(), Spades)
    )
  )

  val pair: Hand = Hand(
    List(
      Card(Three(), Spades),
      Card(Three(), Hearts),
      Card(Four(), Clubs),
      Card(Five(), Diamonds),
      Card(Seven(), Spades)
    )
  )




  val aceLowStraightFlush: Hand = Hand(
    List(
      Card(Three(), Spades),
      Card(Ace(), Spades),
      Card(Five(), Spades),
      Card(Two(), Spades),
      Card(Four(), Spades)

    )
  )

  val aceLowStraight: Hand = Hand(
    List(
      Card(Three(), Spades),
      Card(Ace(), Hearts),
      Card(Five(), Spades),
      Card(Two(), Spades),
      Card(Four(), Spades)
    )
  )





  

  

  val isUnOrderedStraight: Hand = Hand(List(
    Card(Three(), Spades),
    Card(Seven(), Spades),
    Card(Five(), Spades),
    Card(Six(), Spades),
    Card(Four(), Spades))
  )

  val highCardOnly: Hand = Hand(List(
    Card(Two(), Spades),
    Card(Seven(), Spades),
    Card(Five(), Spades),
    Card(Six(), Hearts),
    Card(Four(), Spades))
  )
