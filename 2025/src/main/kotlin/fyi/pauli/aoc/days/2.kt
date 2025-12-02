package fyi.pauli.aoc.days

import fyi.pauli.aoc.day

val day2 = day(2) {

    val productIds: MutableList<String> = input.split(",").toMutableList()
    val ranges: MutableList<LongRange> = productIds.map {
        val (first, second) = it.split("-").map(String::trim).map(String::toLong)
        first..second
    }.toMutableList()

    first = {
        ranges.sumOf { range ->
            range
                .map(Long::toString)
                .filter { it.length % 2 == 0 }
                .filter { it.take(it.length / 2) == it.takeLast(it.length / 2) }
                .sumOf(String::toLong)
        }
    }

    second = {

    }
}