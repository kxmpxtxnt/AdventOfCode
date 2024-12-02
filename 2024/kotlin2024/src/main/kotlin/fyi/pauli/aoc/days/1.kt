package fyi.pauli.aoc.days

import fyi.pauli.aoc.common.AdventOfCode
import fyi.pauli.aoc.common.day.Day
import fyi.pauli.aoc.common.day.day
import kotlin.math.abs

val AdventOfCode.first: Day
  get() = day(this, 1) {
    fun leftRight(): Pair<MutableList<Int>, MutableList<Int>> {
      val (left, right) = (0..1).map { i ->
        inputLines.map { l -> l.split("   ")[i].toInt() }.sorted()
      }
      return left.toMutableList() to right.toMutableList()
    }

    first {
      val (left, right) = leftRight()
      var overall = 0

      while (left.isNotEmpty() || right.isNotEmpty()) {
        val l = left.removeFirst()
        val r = right.removeFirst()
        overall += abs(r - l)
      }

      overall
    }

    second {
      val (left, right) = leftRight()
      var overall = 0

      left.forEach { t ->
        overall += t * right.count { i -> i == t }
        right.removeIf { i -> i == t }
      }

      overall
    }
  }