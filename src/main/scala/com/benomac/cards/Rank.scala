package com.benomac.cards
import com.benomac.cards.Rank

trait Rank:
  val score: Int
  def name: String

object Rank:
  // TODO add checker to classes to make sure the number provided is correct
  // change so that ace's value is always high, then change aceChecked to do the opposite.
  // as you really should be treating aces as high unless they're in a 1 - 5 straight
  case class Ace(override val score: Int = 1) extends Rank {
    override def name: String = "Ace"
  }

  case class Two(override val score: Int = 2) extends Rank {
    override def name: String = "Two"
  }

  case class Three(override val score: Int = 3) extends Rank {
    override def name: String = "Three"
  }

  case class Four(override val score: Int = 4) extends Rank {
    override def name: String = "Four"
  }

  case class Five(override val score: Int = 5) extends Rank {
    override def name: String = "Five"
  }

  case class Six(override val score: Int = 6) extends Rank {
    override def name: String = "Six"
  }

  case class Seven(override val score: Int = 7) extends Rank {
    override def name: String = "Seven"
  }

  case class Eight(override val score: Int = 8) extends Rank {
    override def name: String = "Eight"
  }

  case class Nine(override val score: Int = 9) extends Rank {
    override def name: String = "Nine"
  }

  case class Ten(override val score: Int = 10) extends Rank {
    override def name: String = "Ten"
  }

  case class Jack(override val score: Int = 11) extends Rank {
    override def name: String = "Jack"
  }

  case class Queen(override val score: Int = 12) extends Rank {
    override def name: String = "Queen"
  }

  case class King(override val score: Int = 13) extends Rank {
    override def name: String = "King"
  }

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


