package fyi.pauli.aoc.days

import fyi.pauli.aoc.common.AdventOfCode
import fyi.pauli.aoc.common.day.Day
import fyi.pauli.aoc.common.day.day

val AdventOfCode.second: Day
  get() = day(this, 2) {
    var reports = inputLines.map { report -> report.split(" ").map(String::toInt) }.toMutableList()

    fun valid(report: List<Int>): Int {
      val hasIncreased = report[1] > report[0]
      var problems = 0
      report.forEachIndexed { index, current ->
        if (hasIncreased && !(1..3).contains(current - report.getOrElse(index - 1) { return@forEachIndexed }))
          problems++
        if (!hasIncreased && !(1..3).contains(report.getOrElse(index - 1) { return@forEachIndexed } - current))
          problems++
      }

      return problems
    }

    first {
      reports.count {
        valid(it) == 0
      }
    }

    second {
      reports.count {
        valid(it) <= 1
      }
    }
  }