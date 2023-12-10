import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.modules.SerializersModule

@OptIn(ExperimentalSerializationApi::class)
class AocListDecoder(override val serializersModule: SerializersModule, val split: List<String>) : CommonDecoder() {
    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
        return this
    }

    override fun decodeCollectionSize(descriptor: SerialDescriptor): Int {
        return split.size
    }

    override fun decodeIntElement(descriptor: SerialDescriptor, index: Int): Int {
        TODO("Not yet implemented")
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

    override fun <T> decodeSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T>,
        previousValue: T?
    ): T {
        val helper = Helper(serializersModule)
        val decoder = helper.buildDecoder<T>(
            split[index],
            descriptor.getElementDescriptor(index).getElementDescriptor(index)
        )
        return deserializer.deserialize(decoder)
    }

    override fun decodeShortElement(descriptor: SerialDescriptor, index: Int): Short {
        TODO("Not yet implemented")
    }

    override fun decodeStringElement(descriptor: SerialDescriptor, index: Int): String {
        TODO("Not yet implemented")
    }

    override fun endStructure(descriptor: SerialDescriptor) {
        TODO("Not yet implemented")
    }


}