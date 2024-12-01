package fyi.pauli.aoc.common

import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyles.bold
import com.github.ajalt.mordant.rendering.TextStyles.reset
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.terminal.prompt
import fyi.pauli.aoc.common.day.Day
import kotlin.system.exitProcess

data class AdventOfCode(
  val year: Int,
) {

  val prefix =
    (bold + green)("AdventOfCode") +
        (reset + gray)(" - ") +
        (bold + red)(year.toString()) +
        (reset + gray)(" | ")

  val terminal: Terminal = Terminal(
    AnsiLevel.TRUECOLOR
  )

  internal val internalDays: MutableList<Day> = mutableListOf()

  internal fun input() {
    while (true) {
      val dayNumber =
        terminal.prompt(prefix + white("Day: "), showChoices = true, choices = internalDays.map(Day::day).map(Int::toString).toMutableList().apply {
          add("exit")
        })

      if (dayNumber == "exit") {
        exitProcess(0)
      }

      if (dayNumber == null) return
      internalDays.find { it.day == dayNumber.toInt() }!!.execute()
    }
  }
}

operator fun AdventOfCode.plusAssign(day: Day) {
  internalDays += day
}

operator fun AdventOfCode.plusAssign(days: Collection<Day>) {
  internalDays += days
}


fun adventOfCode(
  year: Int,
  init: AdventOfCode.() -> Unit,
): Unit = AdventOfCode(year).run {
  apply(init)
  input()
}