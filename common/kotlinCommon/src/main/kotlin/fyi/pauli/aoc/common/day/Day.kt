package fyi.pauli.aoc.common.day

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.info
import fyi.pauli.aoc.common.AdventOfCode
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.io.path.readText

data class Day(
  val day: Int,
  val advent: AdventOfCode,
) {

  private val inputPath by lazy { Path("./inputs/${advent.year}").resolve(day.toString().padStart(2, '0') + ".txt") }

  val inputList: List<String> by lazy { inputPath.readLines() }
  val input: String by lazy { inputPath.readText() }
  val inputWords: List<String> by lazy { input.split(" ") }

  private var first: () -> String = { "" }
  private val second: () -> String = { "" }

  fun first(block: () -> String) {
    first = block
  }

  fun second(block: () -> String) {
    first = block
  }

  fun executePart(part: () -> String) {
    val partNumber = if (part == first) 1 else 2
    advent.terminal.info(
      advent.prefix + TextColors.white("Executing part $partNumber")
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