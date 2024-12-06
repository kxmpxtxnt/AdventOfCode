package fyi.pauli.aoc.days

import fyi.pauli.aoc.common.AdventOfCode
import fyi.pauli.aoc.common.day.Day
import fyi.pauli.aoc.common.day.day

typealias Direction = Pair<Int, Int>
typealias Position = Triple<Int, Int, Direction>

val AdventOfCode.sixth: Day
  get() = day(this, 6) {
    val map = inputLines.map { it.toCharArray() }

    fun calculateNextPosition(row: Int, col: Int, facing: Direction): Position {
      val (dRow, dCol) = facing
      val newRow = row + dRow
      val newCol = col + dCol

      return if (newRow in map.indices && newCol in map[0].indices) {
        if (map[newRow][newCol] == '.') {
          Position(newRow, newCol, facing)
        } else {
          Position(row, col, Direction(dCol, -dRow))
        }
      } else {
        Position(-1, -1, facing)
      }
    }

    val initialPosition = map.indices
      .asSequence()
      .flatMap { row ->
        map[row].indices
          .asSequence()
          .filter { col -> map[row][col] == '^' }
          .map { col -> Position(row, col, Direction(-1, 0)) }
      }
      .first()

    var (row, col, facing) = initialPosition

    map[row][col] = '.'

    first {
      generateSequence(Position(row, col, facing)) { (r, c, dir) ->
        calculateNextPosition(r, c, dir).takeIf { it.first != -1 }
      }
        .map { Pair(it.first, it.second) }
        .toSet()
        .size
    }

    second {
      fun detectsCycle(startRow: Int, startCol: Int, initialDirection: Direction): Boolean {
        val seenPositions = mutableSetOf(Position(startRow, startCol, initialDirection))
        var (currentRow, currentCol, currentDir) = Position(startRow, startCol, initialDirection)

        while (currentRow != -1) {
          val (nextRow, nextCol, nextDir) = calculateNextPosition(currentRow, currentCol, currentDir)

          if (seenPositions.contains(Position(nextRow, nextCol, nextDir))) {
            return true
          }
          seenPositions.add(Position(nextRow, nextCol, nextDir))
          currentRow = nextRow
          currentCol = nextCol
          currentDir = nextDir
        }

        return false
      }

      map.indices
        .asSequence()
        .flatMap { row ->
          map[row].indices.asSequence()
            .filter { col -> map[row][col] !in listOf('#', '^') }
            .map { col ->
              map[row][col] = '#'
              val cycleDetected = detectsCycle(initialPosition.first, initialPosition.second, initialPosition.third)
              map[row][col] = '.'
              cycleDetected
            }
        }
        .count { it }
    }
  }