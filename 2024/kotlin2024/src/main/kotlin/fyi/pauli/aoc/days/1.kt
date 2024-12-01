package fyi.pauli.aoc.days

import fyi.pauli.aoc.common.AdventOfCode
import fyi.pauli.aoc.common.day.Day
import fyi.pauli.aoc.common.day.day
import kotlin.math.abs

val AdventOfCode.first: Day
  get() = day(this, 1) {
    fun leftRight(applier: MutableList<Int>.() -> Unit = {}): Pair<MutableList<Int>, MutableList<Int>> {
      val words = input
        .split("\n")
        .map { it.split("   ") }

      val left = mutableListOf<Int>()
      val right = mutableListOf<Int>()

      words.forEach { strings ->
        left.add(strings[0].toInt())
        right.add(strings[1].toInt())
      }

      return left.apply(applier) to right.apply(applier)
    }

    first {
      val (left, right) = leftRight(MutableList<Int>::sort)
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
        right.removeIf { i -> i == t  }
      }

      overall
    }
  }