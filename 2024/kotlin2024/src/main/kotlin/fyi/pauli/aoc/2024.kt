package fyi.pauli.aoc

import fyi.pauli.aoc.common.adventOfCode
import fyi.pauli.aoc.common.plusAssign
import fyi.pauli.aoc.days.*

fun main(): Unit = adventOfCode(2024) {

  this += listOf(
    first,
    second,
    third,
    forth,
    fifth,
    sixth
  )
}