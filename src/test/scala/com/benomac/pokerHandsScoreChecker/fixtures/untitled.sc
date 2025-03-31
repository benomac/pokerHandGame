import com.benomac.cards.*
import com.benomac.cards.Suit.*
import com.benomac.cards.Value.*

import com.benomac.pokerHandsScoreChecker.fixtures.HandsFixtures.*

val twoPair: Hand = Hand(
  List(
    Card(Three(), Spades),
    Card(Three(), Hearts),
    Card(Four(), Clubs),
    Card(Five(), Diamonds),
    Card(Four(), Spades)
  )
)