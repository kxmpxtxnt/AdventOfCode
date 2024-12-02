package fyi.pauli.aoc.days

import fyi.pauli.aoc.common.AdventOfCode
import fyi.pauli.aoc.common.day.Day
import fyi.pauli.aoc.common.day.day
import kotlin.math.abs

val AdventOfCode.first: Day
  get() = day(this, 1) {

    val (left, right) = (0..1).map {
      inputLines.map { line -> line.split("   ")[it].toInt() }.sorted()
    }

    first {
      left.zip(right).sumOf { (l, r) -> abs(r - l) }
    }


    second {
      left.groupingBy { it }.eachCount().entries.sumOf { (value, count) ->
        value * count
      }
    }
  }