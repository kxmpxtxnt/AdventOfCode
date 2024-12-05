package fyi.pauli.aoc.days

import fyi.pauli.aoc.common.AdventOfCode
import fyi.pauli.aoc.common.day.Day
import fyi.pauli.aoc.common.day.day

val AdventOfCode.fifth: Day
  get() = day(this, 5) {
    val rules = inputLines.takeWhile { it.isNotEmpty() }
      .map { it.split("|").map(String::toInt).let { pair -> pair[0] to pair[1] } }
    val changes = inputLines.dropWhile { it.isNotEmpty() }.drop(1)
      .map { it.split(",").map(String::toInt) }

    val before = buildMap<Int, MutableList<Int>> {
      rules.forEach { (x, y) ->
        putIfAbsent(x, mutableListOf()).apply { getOrElse(x) { null }?.add(y) }
      }
    }

    fun valid(seq: List<Int>): Boolean =
      seq.windowed(2).all { (current, next) -> before[current]?.contains(next) != false }

    first {
      changes.filter { valid(it) }
        .sumOf { it[it.size / 2] }
    }

    second {
      changes.filterNot { valid(it) }
        .sumOf {
          it.sortedWith { a, b ->
            if (b in (before[a] ?: emptyList())) -1 else 1
          }[it.size / 2]
        }
    }
  }