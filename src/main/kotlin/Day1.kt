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
