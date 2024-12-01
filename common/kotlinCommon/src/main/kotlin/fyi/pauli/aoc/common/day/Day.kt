package fyi.pauli.aoc.common.day

import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyles.italic
import com.github.ajalt.mordant.terminal.info
import fyi.pauli.aoc.common.AdventOfCode
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.io.path.readText
import kotlin.time.measureTimedValue

data class Day(
  val day: Int,
  val advent: AdventOfCode,
) {

  private val inputPath by lazy { Path("./inputs/${advent.year}").resolve(day.toString().padStart(2, '0') + ".txt") }

  val inputLines: List<String> by lazy { inputPath.readLines() }
  val input: String by lazy { inputPath.readText() }
  val inputWords: List<String> by lazy { input.split(" ") }

  private var first: () -> Any = { "" }
  private var second: () -> Any = { "" }

  fun first(block: () -> Any) {
    first = block
  }

  fun second(block: () -> Any) {
    second = block
  }

  private fun executePart(part: () -> Any) {
    val partNumber = if (part == first) 1 else 2
    val result = measureTimedValue(part)
    advent.terminal.info(
      advent.prefix +
          white("Result for part ") +
          brightGreen(partNumber.toString()) +
          white(" is ") +
          (italic + brightRed)(result.value.toString()) +
          white(". Took ") +
          brightGreen("${result.duration.inWholeMilliseconds}ms") +
          white(".")
    )
  }

  fun execute() {
    executePart(first)
    executePart(second)
  }
}

fun day(advent: AdventOfCode, day: Int, init: Day.() -> Unit = {}) = Day(day, advent).apply(init)

operator fun Double.invoke(advent: AdventOfCode, init: Day.() -> Unit = {}) =
  day(advent, this.toString().split(".")[1].toInt(), init)
