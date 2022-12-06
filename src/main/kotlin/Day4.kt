object Day4: Day(4) {

    private fun parsePairs(input: String): Pair<Pair<Int, Int>, Pair<Int, Int>> {
        val pairs = input.split(",").map { pairs ->
            pairs.split("-").map {
                it.toInt()
            }
        }

        return (pairs[0][0] to pairs[0][1]) to (pairs[1][0] to pairs[1][1])
    }

    private fun isWhollyContained(pair1: Pair<Int, Int>, pair2: Pair<Int, Int>): Boolean {
        return (pair1.first - pair2.first) * (pair1.second - pair2.second) <= 0
    }

    private fun areOverlapping(pair1: Pair<Int, Int>, pair2: Pair<Int, Int>): Boolean {
        return (pair1.first - pair2.second) * (pair1.second - pair2.first) <= 0
    }

    override fun getSolution(): Solution {
        var containedPairs = 0
        var overlappingPairs = 0
        getInputFile().forEachLine {
            val pairs = parsePairs(it)
            if (isWhollyContained(pairs.first, pairs.second)) {
                containedPairs++
            }
            if (areOverlapping(pairs.first, pairs.second)) {
                overlappingPairs++
            }
        }
        return Solution(
            containedPairs.toString(),
            overlappingPairs.toString()
        )
    }
}