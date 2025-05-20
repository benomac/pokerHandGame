List(1, 2, 3).diff(List(4, 5, 6))
List(1, 2, 3).take(2)

3 % 2
List(1, 2, 3, 4, 5).sliding(2)
List(1, 2, 3, 4, 5).sliding(2).forall{
    case List(a, b) => a + 1 == b
    case _ => false
}

List(List(1, 2), List(2, 3), List(3, 4), List(4, 5))