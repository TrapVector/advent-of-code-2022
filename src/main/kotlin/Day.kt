import java.io.File

class Solution(val part1: String = "???", val part2: String = "???")

open class Day(val index: Int) {
    open fun getSolution(): Solution = Solution()

    private fun getInputFileName() = "inputs/day$index.txt"
    fun getInputFile() = File(getInputFileName())
}