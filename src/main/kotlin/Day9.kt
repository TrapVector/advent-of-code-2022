import kotlin.math.*

typealias Point = Pair<Int, Int>
object Day9 : Day(9) {

    private infix operator fun Point.plus(other: Point): Point {
        return Point(this.first + other.first, this.second + other.second)
    }

    private infix operator fun Point.minus(other: Point): Point {
        return Point(this.first - other.first, this.second - other.second)
    }

    private fun clamp(i: Int) = max(-1, min(1, i))
    private fun clamp(p: Point) = Point(clamp(p.first), clamp(p.second))

    private fun isTooFar(p: Point) = abs(p.first) > 1 || abs(p.second) > 1

    override fun getSolution(): Solution {
        val part1Spaces: MutableSet<Point> = mutableSetOf()
        val part2Spaces: MutableSet<Point> = mutableSetOf()

        val knotCount = 10
        val knotPositions = MutableList(knotCount) { Point(0, 0) }

        part1Spaces.add(knotPositions[1])
        part2Spaces.add(knotPositions[knotCount - 1])

        getInputFile().forEachLine {
            val parts = it.split(' ')
            val headOffset = when (parts[0]) {
                "U" -> Point(0, -1)
                "D" -> Point(0, 1)
                "R" -> Point(1, 0)
                "L" -> Point(-1, 0)
                else -> throw UnsupportedOperationException("Unknown direction '$parts[0]'")
            }

            repeat(parts[1].toInt()) {
                knotPositions[0] += headOffset

                for (i in 1 until knotCount) {
                    val prevKnotPosition = knotPositions[i - 1]
                    val tailOffset = prevKnotPosition - knotPositions[i]
                    if (isTooFar(tailOffset)) {
                        knotPositions[i] += clamp(tailOffset)
                    }
                }

                part1Spaces.add(knotPositions[1])
                part2Spaces.add(knotPositions[knotCount - 1])
            }
        }

        return Solution(
            part1Spaces.count().toString(),
            part2Spaces.count().toString(),
        )
    }
}