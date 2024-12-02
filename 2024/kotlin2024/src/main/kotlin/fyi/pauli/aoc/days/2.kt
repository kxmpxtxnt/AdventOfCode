package fyi.pauli.aoc.days

import fyi.pauli.aoc.common.AdventOfCode
import fyi.pauli.aoc.common.day.Day
import fyi.pauli.aoc.common.day.day

val AdventOfCode.second: Day
  get() = day(this, 2) {
    var reports = inputLines.map { report -> report.split(" ").map(String::toInt) }

    fun problems(report: List<Int>): Int {
      val hasIncreased = report[1] > report[0]
      return report.zipWithNext().count { (prev, current) ->
        val delta = if (hasIncreased) current - prev else prev - current
        delta !in 1..3
      }
    }

    first {
      reports.count { problems(it) == 0 }
    }

    second {
      reports.count { problems(it) <= 1 }
    }
  }