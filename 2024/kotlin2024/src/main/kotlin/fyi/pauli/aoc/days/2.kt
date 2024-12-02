package fyi.pauli.aoc.days

import fyi.pauli.aoc.common.AdventOfCode
import fyi.pauli.aoc.common.day.Day
import fyi.pauli.aoc.common.day.day

val AdventOfCode.second: Day
  get() = day(this, 2) {
    fun valid(report: List<Int>): Boolean {
      val hasIncreased = report[1] > report[0]
      report.forEachIndexed { index, current ->
        if (hasIncreased && !(1..3).contains(current - report.getOrElse(index - 1) { return@forEachIndexed })) return false
        if (!hasIncreased && !(1..3).contains(report.getOrElse(index - 1) { return@forEachIndexed } - current)) return false
      }

      return true
    }

    first {
      inputLines.map { report -> report.split(" ").map(String::toInt) }.count(::valid)
    }

    second {

    }
  }