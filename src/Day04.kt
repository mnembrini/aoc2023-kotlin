import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import java.util.*

fun main() {

    fun part1(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            // Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            val gameId = line.substringBefore(":").substringAfter("Card ").trim().toInt()
            val parts = line.substringAfter(":").split("|")
                .map {
                    it.trim().split(Regex("\\s+"))
                        .map { it.toInt() }
                        .toMutableSet()
                }
            val winning = parts[0]
            val values = parts[1]

            values.retainAll(winning)
            if (!values.isEmpty()) {
                val score = 1 shl (values.size - 1)
                sum += score
                println("Game $gameId score: $score")
            }


        }


        return sum;
    }

    fun part2(input: List<String>): Int {
        var multipliers = Array(input.size) {1}
        for (line in input) {
            // Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            val gameId = line.substringBefore(":").substringAfter("Card ").trim().toInt()
            val parts = line.substringAfter(":").split("|")
                .map {
                    it.trim().split(Regex("\\s+"))
                        .map { it.toInt() }
                        .toMutableSet()
                }
            val winning = parts[0]
            val values = parts[1]

            values.retainAll(winning)
            if (!values.isEmpty()) {

                for (j in 1..multipliers[gameId - 1]) {
                    for (i in gameId until gameId + values.size) {
                        multipliers[i]++
                    }
                }



                println("Game $gameId score: ${values.size}")
            }


        }

        return multipliers.sum();
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()

}
