package com.benomac.cards
import com.benomac.cards.Rank

trait Rank:
  val score: Int

object Rank:
  // TODO add checker to classes to make sure the number provided is correct
  // change so that ace's value is always high, then change aceChecked to do the opposite.
  // as you really should be treating aces as high unless they're in a 1 - 5 straight
  case class Ace(override val score: Int = 1) extends Rank

  case class Two(override val score: Int = 2) extends Rank

  case class Three(override val score: Int = 3) extends Rank

  case class Four(override val score: Int = 4) extends Rank

  case class Five(override val score: Int = 5) extends Rank

  case class Six(override val score: Int = 6) extends Rank

  case class Seven(override val score: Int = 7) extends Rank

  case class Eight(override val score: Int = 8) extends Rank

  case class Nine(override val score: Int = 9) extends Rank

  case class Ten(override val score: Int = 10) extends Rank

  case class Jack(override val score: Int = 11) extends Rank

  case class Queen(override val score: Int = 12) extends Rank

  case class King(override val score: Int = 13) extends Rank

  val ranks: List[Rank] = List(
    Ace(),
    Two(),
    Three(),
    Four(),
    Five(),
    Six(),
    Seven(),
    Eight(),
    Nine(),
    Ten(),
    Jack(),
    Queen(),
    King()
  )


