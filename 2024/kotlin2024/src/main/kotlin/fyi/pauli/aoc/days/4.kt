package fyi.pauli.aoc.days

import fyi.pauli.aoc.common.AdventOfCode
import fyi.pauli.aoc.common.day.Day
import fyi.pauli.aoc.common.day.day

val AdventOfCode.forth: Day
  get() = day(this, 4) {
    fun chars(
      target: Char,
      position: Pair<Int, Int>,
      delta: Pair<Int, Int>? = null,
    ): List<Pair<Int, Int>> {
      val (currentRow, currentCol) = position
      val rows = inputLines.size
      val cols = inputLines.first().length

      return (delta?.let { listOf(currentRow + it.first to currentCol + it.second) } ?: (-1..1)
        .flatMap { r ->
          (-1..1).map { c -> currentRow + r to currentCol + c }
        })
        .filter { (r, c) ->
          r in 0 until rows && c in 0 until cols && inputLines[r][c] == target
        }
    }


    first {
      inputLines.indices.sumOf { row ->
        inputLines[row].indices.sumOf { col ->
          if (inputLines[row][col] != 'X') 0
          else chars('M', row to col).count { (mRow, mCol) ->
            val rowDiff = mRow - row
            val colDiff = mCol - col

            chars('A', row to col, 2 * rowDiff to 2 * colDiff).isNotEmpty() &&
                chars('S', row to col, 3 * rowDiff to 3 * colDiff).isNotEmpty()
          }
        }
      }
    }

    second {
      inputLines.indices.sumOf { row ->
        inputLines[row].indices.count col@{ col ->
          if (inputLines[row][col] != 'A') return@col false

          chars('M', row to col)
            .firstOrNull { (mRow, mCol) -> mRow != row && mCol != col }
            ?.let { (mRow, mCol) ->
              val rowOffset = mRow - row
              val colOffset = mCol - col

              chars('S', row to col, -rowOffset to -colOffset).isNotEmpty() &&
                  (chars('S', row to col, rowOffset to -colOffset).isNotEmpty() &&
                      chars('M', row to col, -rowOffset to colOffset).isNotEmpty() ||
                      chars('M', row to col, rowOffset to -colOffset).isNotEmpty() &&
                      chars('S', row to col, -rowOffset to colOffset).isNotEmpty())
            } == true
        }
      }
    }
  }