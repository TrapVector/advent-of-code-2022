object Day8 : Day(8) {

    private infix fun <T> MutableList<MutableList<T>>.column(index: Int) = this.map { it[index] }

    override fun getSolution(): Solution {
        val trees: MutableList<MutableList<Int>> = mutableListOf()

        getInputFile().forEachLine {
            val row = mutableListOf(*it.map { c -> c - '0' }.toTypedArray())
            trees.add(row)
        }

        //val visbility = List<List<Boolean>>(trees.count()) {
        //    List<Boolean>(trees[0].count()) { false }
        //}

        var visibleCount = 0
        var maxScenicScore = 0

        trees.forEachIndexed { y, row ->
            row.forEachIndexed { x, height ->
                val column = trees.column(x)

                val visible = row.drop(x + 1).all { it < height }
                        || row.take(x).all { it < height }
                        || column.drop(y + 1).all { it < height }
                        || column.take(y).all { it < height }

                if (visible) visibleCount++

                val computeScore = {subList: List<Int> ->
                    val index = subList.indexOfFirst { it >= height }
                    if (index == -1) subList.count() else index + 1
                }

                val rightScore = computeScore(row.drop(x + 1))
                val leftScore = computeScore(row.take(x).reversed())
                val downScore = computeScore(column.drop(y + 1))
                val upScore = computeScore(column.take(y).reversed())
                val scenicScore = rightScore * leftScore * downScore * upScore

                if (scenicScore > maxScenicScore) maxScenicScore = scenicScore
            }
        }

        return Solution(
            visibleCount.toString(),
            maxScenicScore.toString()
        )
    }
}