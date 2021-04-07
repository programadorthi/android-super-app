package dev.programadorthi.shared.ui.fake

import dev.programadorthi.shared.ui.resource.StringProvider

class StringProviderFake : StringProvider {
    var stringToReturn = ""

    override fun getString(stringId: Int): String = stringToReturn

    override fun getString(stringId: Int, vararg args: Any): String = stringToReturn
}
