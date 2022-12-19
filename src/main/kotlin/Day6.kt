object Day6 : Day(6) {

    private fun findPacketIndex(line: String, packetSize: Int): Int {
        val startIndex = line.windowed(packetSize).indexOfFirst {
            it.toSet().count() == packetSize
        }
        return startIndex + packetSize
    }
    override fun getSolution(): Solution {
        val line = getInputFile().readLines()[0]
        val headerPacketSize = 4
        val messagePacketSize = 14
        val headerPacketIndex = findPacketIndex(line, headerPacketSize)
        val messagePacketIndex = findPacketIndex(line, messagePacketSize)

        return Solution(
            headerPacketIndex.toString(),
            messagePacketIndex.toString())
    }
}