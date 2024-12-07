package fyi.pauli.aoc.days

import fyi.pauli.aoc.common.AdventOfCode
import fyi.pauli.aoc.common.day.Day
import fyi.pauli.aoc.common.day.day

val AdventOfCode.seventh: Day
  get() = day(this, 7) {
    val actions: Map<String, (Long, Long) -> Long> = mapOf(
      "*" to { i, j -> i * j },
      "+" to { i, j -> i + j },
      "||" to { i, j -> "$i$j".toLong() }
    )

    fun getOperations(): List<Pair<Long, List<Long>>> = inputLines
      .map { line -> line.split(": ") }
      .map { parts -> parts[0].toLong() to parts[1].split(" ").map(String::toLong) }

    fun validSequences(allowed: List<String>): Long = getOperations().sumOf { (solution, sequence) ->
      fun evaluateWithActions(index: Int, current: Long): Boolean {
        if (index == sequence.size) return current == solution

        return actions.filterKeys(allowed::contains).values.any { operation ->
          evaluateWithActions(index + 1, operation(current, sequence[index]))
        }
      }

      if (evaluateWithActions(1, sequence[0])) solution else 0L
    }

    first {
      validSequences(listOf("*", "+"))
    }
    second {
      validSequences(listOf("*", "+", "||"))
    }
  }