package dev.programadorthi.shared.network.fake

import dev.programadorthi.shared.network.mapper.Mapper
import dev.programadorthi.shared.network.mapper.RemoteMapper

class RemoteMapperFake<Raw, Model> : RemoteMapper<Raw, Model>() {
    private val fields = mutableSetOf<String>()
    lateinit var mapper: Mapper<Raw, Model>

    override fun checkEssentialParams(missingFields: MutableSet<String>, raw: Raw) {
        missingFields += fields
    }

    override fun mapRawToModel(raw: Raw): Model = mapper.invoke(raw)

    fun addMissingField(vararg field: String) {
        fields += field
    }
}
