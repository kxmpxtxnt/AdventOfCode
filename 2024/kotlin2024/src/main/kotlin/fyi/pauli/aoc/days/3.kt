package fyi.pauli.aoc.days

import fyi.pauli.aoc.common.AdventOfCode
import fyi.pauli.aoc.common.day.Day
import fyi.pauli.aoc.common.day.day

val AdventOfCode.third: Day
  get() = day(this, 3) {

    val mul = Regex(pattern = "mul\\((\\s*\\d+\\s*),(\\s*\\d+\\s*)\\)")

    first {
      mul.findAll(input).map(MatchResult::groupValues).sumOf { it[1].toInt() * it[2].toInt() }
    }

    val enabledMul = Regex(pattern = "mul\\((\\s*\\d+\\s*),(\\s*\\d+\\s*)\\)|(?:do\\(\\)|don't\\(\\))")

    second {
      var enabled = true
      input.trim().let { enabledMul.findAll(it) }.sumOf { result ->
        when (result.value) {
          "do()" -> enabled = true
          "don't()" -> enabled = false
        }
        result.takeIf { enabled && it.value.startsWith("mul") }
          ?.groupValues?.drop(1)?.map(String::toInt)?.let { (a, b) -> a * b } ?: 0
      }
    }
  }
