import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.modules.SerializersModule

@OptIn(ExperimentalSerializationApi::class)
class AocDecoder(override val serializersModule: SerializersModule, val match: MatchResult) : CommonDecoder() {
    
    override fun decodeIntElement(descriptor: SerialDescriptor, index: Int): Int {
        val name = descriptor.getElementName(index)
        return match!!.groups.get(name)!!.value.toInt()
    }

    override fun decodeLongElement(descriptor: SerialDescriptor, index: Int): Long {
        TODO("Not yet implemented")
    }

    @ExperimentalSerializationApi
    override fun <T : Any> decodeNullableSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T?>,
        previousValue: T?
    ): T? {
        TODO("Not yet implemented")
    }


    override fun decodeSequentially(): Boolean {
        return false
    }

    override fun <T> decodeSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T>,
        previousValue: T?
    ): T {
        val helper = Helper(serializersModule)
        val decoder = helper.buildDecoder<T>(
            match.groups.get(descriptor.getElementName(index))!!.value,
            descriptor.getElementDescriptor(index)
        )
        return deserializer.deserialize(decoder)
    }

    override fun decodeShortElement(descriptor: SerialDescriptor, index: Int): Short {
        TODO("Not yet implemented")
    }

    override fun decodeStringElement(descriptor: SerialDescriptor, index: Int): String {
        val name = descriptor.getElementName(index)
        return match!!.groups.get(name)!!.value
    }

    override fun endStructure(descriptor: SerialDescriptor) {

    }

    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
        return this
    }

 


}