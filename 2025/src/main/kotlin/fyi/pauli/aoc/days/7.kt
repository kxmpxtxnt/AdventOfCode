package fyi.pauli.aoc.days

import fyi.pauli.aoc.day

val day7 = day(7) {
    first = {
        val visited = mutableSetOf<Pair<Int, Int>>()
        val queue = mutableListOf(
            0 to inputLines.first().indexOf('S')
        )

        generateSequence(queue::removeFirstOrNull)
            .sumOf { (startRow, col) ->
                inputLines.asSequence()
                    .drop(startRow)
                    .withIndex()
                    .firstOrNull { it.value[col] == '^' }
                    ?.let { (row, _) ->
                        val pos = startRow + row to col
                        if (visited.add(pos)) {
                            if (col > 0) queue.add(pos.first to col - 1)
                            if (col < inputLines[pos.first].lastIndex) queue.add(pos.first to col + 1)
                            1
                        } else 0
                    } ?: 0
            }
    }
}