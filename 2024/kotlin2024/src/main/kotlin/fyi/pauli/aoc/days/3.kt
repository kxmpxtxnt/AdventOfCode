package fyi.pauli.aoc.days

import fyi.pauli.aoc.common.AdventOfCode
import fyi.pauli.aoc.common.day.Day
import fyi.pauli.aoc.common.day.day

val AdventOfCode.third: Day
  get() = day(this, 3) {

    val pattern = Regex(pattern = "mul\\((\\s*\\d+\\s*),(\\s*\\d+\\s*)\\)")

    first {
      pattern.findAll(input).map(MatchResult::groupValues).sumOf { it[1].toInt() * it[2].toInt() }
    }

    second {

    }
  }