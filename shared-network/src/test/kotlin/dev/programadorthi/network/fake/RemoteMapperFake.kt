package dev.programadorthi.network.fake

import dev.programadorthi.network.mapper.RemoteMapper

class RemoteMapperFake(var throwException: Boolean = false) : RemoteMapper<String, Int>() {

    override fun checkEssentialParams(missingFields: MutableList<String>, raw: String) {
        if (throwException) {
            missingFields.add("field_missing")
        }
    }

    override fun mapRawToModel(raw: String): Int {
        return Integer.MAX_VALUE
    }
}