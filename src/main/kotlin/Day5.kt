object Day5 : Day(5) {

    private fun parseStackLine(line: String): List<Char> {
        return line.windowed(3, 4) { it[1] }
    }

    private fun modifyStacksReversed(stacks: List<MutableList<Char>>, operands: List<Int>) {
        val quantity = operands[0]
        val source = operands[1] - 1
        val destination = operands[2] - 1

        for (i in 1..quantity) {
            val removed = stacks[source].removeLast()
            stacks[destination].add(removed)
        }
    }

    private fun modifyStacksInOrder(stacks: List<MutableList<Char>>, operands: List<Int>) {
        val quantity = operands[0]
        val source = operands[1] - 1
        val destination = operands[2] - 1

        val removed: MutableList<Char> = mutableListOf()
        for (i in 0 until quantity) {
            val top = stacks[source].removeLast()
            removed.add(top)
        }

        for (letter in removed.reversed()) {
            stacks[destination].add(letter)
        }
    }

    override fun getSolution(): Solution {
        val stackCount = 9
        val stacksPart1: List<MutableList<Char>> = List(stackCount) { mutableListOf() }
        val stacksPart2: List<MutableList<Char>> = List(stackCount) { mutableListOf() }

        val parseInitialStacks = { l: String ->
            val row = parseStackLine(l)
            row.forEachIndexed() { i, c ->
                if (c.isLetter() ) {
                    stacksPart1[i].add(0, c)
                    stacksPart2[i].add(0, c)
                }
            }
            l.isBlank()
        }

        val parseChanges = { l: String ->
            val regex = Regex("""move (\d+) from (\d+) to (\d+)""")
            val result = regex.matchEntire(l)
            if (result != null) {
                modifyStacksReversed(stacksPart1, result.groupValues.drop(1).map { it.toInt() })
                modifyStacksInOrder(stacksPart2, result.groupValues.drop(1).map { it.toInt() })
            }
            false
        }

        val steps = listOf(parseInitialStacks, parseChanges)
        val stepIterator = steps.listIterator()
        var currentStep = stepIterator.next()

        getInputFile().forEachLine {
            val shouldAdvance = currentStep(it)
            if (shouldAdvance && stepIterator.hasNext()) {
                currentStep = stepIterator.next()
            }
        }

        return Solution(
            String(stacksPart1.map { it.last() }.toCharArray()),
            String(stacksPart2.map { it.last() }.toCharArray())
        )
    }
}
