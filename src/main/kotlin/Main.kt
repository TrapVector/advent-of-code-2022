fun main(/*args: Array<String>*/) {
    println("Advent of Code 2022")

    val days: List<Day> =
        listOf(
            Day1,
            Day2,
            Day3,
            Day4,
            Day5,
            Day6,
            Day7,
            Day8,
        )

    for (day in days) {
        println(" Day ${day.index}:")
        val solution = day.getSolution()
        println("  Part 1 = ${solution.part1}")
        println("  Part 2 = ${solution.part2}")
        println()
    }
}