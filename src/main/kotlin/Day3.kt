object Day3: Day(3) {

    private fun getCommonLetter(line: String): Char {
        val compartmentLength = line.length / 2
        val compartment1 = line.substring(0, compartmentLength).toSortedSet()
        val compartment2 = line.substring(compartmentLength).toSortedSet()
        val intersection = compartment1.intersect(compartment2)
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
        var total = 0
        getInputFile().forEachLine {
            val letter = getCommonLetter(it)
            total += getLetterPriority(letter)
        }
        return Solution(
            total.toString()
        )
    }
}