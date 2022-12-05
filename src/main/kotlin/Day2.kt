object Day2 : Day(2) {
    private enum class Choice(val index: Int) {
        Rock(0), Paper(1), Scissors(2);

        fun getScore() = index + 1
    }

    private enum class Result(val score: Int) {
        Lose(0), Draw(3), Win(6);
    }

    private fun parseChoice(encoded: String): Choice {
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

    private fun parseIntendedResult(encoded: String): Result {
        return when (encoded) {
            "X" -> Result.Lose
            "Y" -> Result.Draw
            "Z" -> Result.Win
            else -> throw IllegalArgumentException("Unknown encoded choice: $encoded")
        }
    }

    private fun getResult(opponentChoice: Choice, intendedResult: Result): Int {
        val results = mapOf(
            Choice.Rock     to mapOf(Result.Win to Choice.Paper   , Result.Draw to Choice.Rock    , Result.Lose to Choice.Scissors),
            Choice.Paper    to mapOf(Result.Win to Choice.Scissors, Result.Draw to Choice.Paper   , Result.Lose to Choice.Rock),
            Choice.Scissors to mapOf(Result.Win to Choice.Rock    , Result.Draw to Choice.Scissors, Result.Lose to Choice.Paper))

        return intendedResult.score + results[opponentChoice]?.get(intendedResult)?.getScore()!!
    }

    private fun scoreGame(line: String): Int {
        val parts = line.split(" ")
        val opponentChoice = parseChoice(parts[0])
        val playerChoice = parseChoice(parts[1])

        val result = getResult(playerChoice, opponentChoice)
        return playerChoice.getScore() + result
    }

    private fun projectGame(line: String): Int {
        val parts = line.split(" ")
        val opponentChoice = parseChoice(parts[0])
        val intendedResult = parseIntendedResult(parts[1])

        return getResult(opponentChoice, intendedResult)
    }

    override fun getSolution(): Solution {
        var totalPart1Score = 0
        var totalPart2Score = 0
        getInputFile().forEachLine {
            totalPart1Score += scoreGame(it)
            totalPart2Score += projectGame(it)
        }
        return Solution(
            totalPart1Score.toString(),
            totalPart2Score.toString()
        )
    }
}