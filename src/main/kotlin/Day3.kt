object Day3: Day(3) {

    private fun getCompartments(line: String): List<String> {
        val compartmentLength = line.length / 2
        val compartment1 = line.substring(0, compartmentLength)
        val compartment2 = line.substring(compartmentLength)
        return listOf(compartment1, compartment2)
    }

    private fun getCommonLetter(inputs: List<String>): Char {
        var intersection = inputs[0].toSet()
        for (input in inputs.drop(1)) {
            intersection = intersection.intersect(input.toSet())
        }
        return intersection.first()
    }

    private fun getLetterPriority(letter: Char): Int {
        return when (letter) {
            in 'a'..'z' -> letter - 'a' + 1
            in 'A'..'Z' -> letter - 'A' + 27
            else -> throw IllegalArgumentException("Unhandled char '$letter'")
        }
    }

    override fun getSolution(): Solution {
        var part1Total = 0
        getInputFile().forEachLine {
            val letter = getCommonLetter(getCompartments(it))
            part1Total += getLetterPriority(letter)
        }

        var part2Total = 0
        getInputFile().readLines().chunked(3).forEach {
            val letter = getCommonLetter(it)
            part2Total += getLetterPriority(letter)
        }
        return Solution(
            part1Total.toString(),
            part2Total.toString()
        )
    }
}