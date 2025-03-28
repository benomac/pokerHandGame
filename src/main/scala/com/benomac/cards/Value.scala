package com.benomac.cards
import com.benomac.cards.Value

trait Value:
  val score: Int

object Value:
  // TODO add checker to classes to make sure the number provided is correct
  // change so that ace's value is always high, then change aceChecked to do the opposite.
  // as you really should be treating aces as high unless they're in a 1 - 5 straight
  case class Ace(override val score: Int = 1) extends Value

  case class Two(override val score: Int = 2) extends Value

  case class Three(override val score: Int = 3) extends Value

  case class Four(override val score: Int = 4) extends Value

  case class Five(override val score: Int = 5) extends Value

  case class Six(override val score: Int = 6) extends Value

  case class Seven(override val score: Int = 7) extends Value

  case class Eight(override val score: Int = 8) extends Value

  case class Nine(override val score: Int = 9) extends Value

  case class Ten(override val score: Int = 10) extends Value

  case class Jack(override val score: Int = 11) extends Value

  case class Queen(override val score: Int = 12) extends Value

  case class King(override val score: Int = 13) extends Value


