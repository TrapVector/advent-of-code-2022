import java.io.File

class Solution(val part1: String = "???", val part2: String = "???")

open class Day(val index: Int) {
    open fun getSolution(): Solution = Solution()

    private fun getInputFileName() = "inputs/day$index.txt"
    fun getInputFile() = File(getInputFileName())
}

object Day1 : Day(1) {
    private infix fun <T> MutableList<T>.replaceSortedDesc(item: T) where T : Comparable<T> {
        val index = this.indexOfFirst { item >= it }
        if (index != -1) {
            this.add(index, item)
            this.removeLast()
        }
    }

    override fun getSolution(): Solution {
        val highestCalories: MutableList<Int> = mutableListOf(0, 0, 0)
        var currentCalories = 0
        getInputFile().forEachLine {
            if (it.isBlank()) {
                if (currentCalories > highestCalories.last()) {
                    highestCalories.replaceSortedDesc(currentCalories)
                }

                currentCalories = 0
            } else {
                currentCalories += it.toInt()
            }
        }

        return Solution(
            highestCalories.first().toString(),
            highestCalories.sum().toString())
    }
}

object Day2 : Day(2) {
    private enum class Choice(val index: Int) {
        Rock(0), Paper(1), Scissors(2);

        fun getScore() = index + 1
    }

    private fun getChoice(encoded: String): Choice {
        return when (encoded) {
            "A", "X" -> Choice.Rock
            "B", "Y" -> Choice.Paper
            "C", "Z" -> Choice.Scissors
            else -> throw IllegalArgumentException("Unknown encoded choice: $encoded")
        }
    }

    private fun getResult(playerChoice: Choice, opponentChoice: Choice): Int {
        val results = listOf(3, 0, 6)
        val resultIndex = (opponentChoice.index + results.count() - playerChoice.index) % results.count()
        return results[resultIndex]
    }

    private fun scoreGame(line: String): Int {
        val parts = line.split(" ")
        val opponentChoice = getChoice(parts[0])
        val playerChoice = getChoice(parts[1])

        val result = getResult(playerChoice, opponentChoice)
        return playerChoice.getScore() + result
    }

    override fun getSolution(): Solution {
        var totalScore = 0
        getInputFile().forEachLine {
            totalScore += scoreGame(it)
        }
        return Solution(
            totalScore.toString()
        )
    }
}

fun main(/*args: Array<String>*/) {
    println("Advent of Code 2022")

    val days: List<Day> =
        listOf(
            Day1,
            Day2
        )

    for (day in days) {
        println(" Day ${day.index}:")
        val solution = day.getSolution()
        println("  Part 1 = ${solution.part1}")
        println("  Part 2 = ${solution.part2}")
        println()
    }
}