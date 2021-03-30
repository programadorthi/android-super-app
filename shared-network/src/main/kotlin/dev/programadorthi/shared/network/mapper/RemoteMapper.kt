package dev.programadorthi.shared.network.mapper

import dev.programadorthi.shared.network.exception.NetworkingError
import dev.programadorthi.shared.network.exception.NetworkingError.EssentialParamMissing

typealias Mapper<From, To> = (From) -> To

/**
 * Base map used for inheritance to map network response in feature model
 *
 * @param Raw The data returned from server
 * @param Model The feature model created from Raw
 */
abstract class RemoteMapper<Raw, Model> : Mapper<Raw, Model> {
    /**
     * Mapper the raw to feature model
     *
     * @param from The server response data
     * @return A feature model with mapped server data
     * @throws NetworkingError When the server response is not valid
     */
    @Throws(NetworkingError::class)
    override fun invoke(from: Raw): Model {
        assertEssentialParams(from)
        return mapRawToModel(from)
    }

    /**
     * Check if the required parameters were returned from server
     *
     * @param raw The server response data
     * @throws EssentialParamMissing When required data is missing in the server response
     */
    @Throws(EssentialParamMissing::class)
    private fun assertEssentialParams(raw: Raw) {
        val missingFields = mutableListOf<String>()
        checkEssentialParams(missingFields, raw)
        if (missingFields.isNotEmpty()) {
            val params = missingFields.joinToString(prefix = "[", postfix = "]")
            throw EssentialParamMissing(missingParams = params, rawObject = raw!!)
        }
    }

    /**
     * Check if the specific implementation parameters were return from server
     *
     * @param missingFields The missing required fields in the server response
     * @param raw The server response data
     * @return A missing parameters list or empty list when is all ok
     */
    protected abstract fun checkEssentialParams(missingFields: MutableList<String>, raw: Raw)

    /**
     * Create a [Model] using the [Raw] values
     *
     * @param raw The server response data
     * @return A model with the raw's values
     */
    protected abstract fun mapRawToModel(raw: Raw): Model
}
