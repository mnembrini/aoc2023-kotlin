import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule

fun main() {

    fun part1(input: List<String>): Int {
        for (line in input) {
            // example
            // Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            val set = Aoc.decodeFromString(Set.serializer(), " 3 blue, 4 red")
            println(set)


        }
        return 0
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()

}

@OptIn(ExperimentalSerializationApi::class)
@Target(AnnotationTarget.CLASS)
@SerialInfo
annotation class RecapRegex(val regex: String)

@OptIn(ExperimentalSerializationApi::class)
@Target(AnnotationTarget.CLASS)
@SerialInfo
annotation class RecapList(val separator: String)

@Serializable
@RecapRegex(regex = " (?<count>\\d+) (?<color>green|red|blue)")
class Entry(val count: Int, val color: String) {
}

@Serializable  
@RecapList(separator = ",")
class Set(val entries: List<Entry>) {}

@Serializable
@RecapRegex(regex = " (?<count>\\d+) (?<color>green|red|blue),?")
class Game(val id: Int, val sets: List<Set>) {
}


class Helper(val serializersModule: SerializersModule) {


    fun <T> buildDecoder(string: String, descriptor: SerialDescriptor): Decoder {
        var ann = descriptor.annotations.find { it is RecapRegex }
        if (ann == null) {
            ann = descriptor.annotations.find { it is RecapList }
            if (ann == null) {
                TODO()
            }
            val sep = (ann as RecapList).separator
            val split = string.split(sep)
            println("Built list decoder with $split")
            return AocListDecoder(serializersModule, split)

        }
        val regex = (ann as RecapRegex).regex
        val match = Regex(regex).matchEntire(string)!!
        println("Built match decoder with ${match.groups.map { it?.value }}")
        return AocDecoder(serializersModule, match)
    }
}

sealed class Aoc(override val serializersModule: SerializersModule) : StringFormat {

    public companion object Default : Aoc(EmptySerializersModule())

    @OptIn(ExperimentalSerializationApi::class)
    override fun <T> decodeFromString(deserializer: DeserializationStrategy<T>, string: String): T {
        val helper = Helper(serializersModule)
        val decoder = helper.buildDecoder<T>(string, deserializer.descriptor)
        return deserializer.deserialize(decoder)

    }

    override fun <T> encodeToString(serializer: SerializationStrategy<T>, value: T): String {
        TODO("Not Implemented")
    }

}