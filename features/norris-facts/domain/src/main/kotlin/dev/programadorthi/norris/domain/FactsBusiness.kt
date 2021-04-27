package dev.programadorthi.norris.domain

import dev.programadorthi.shared.domain.Result

sealed class FactsBusiness : Result.Business {
    object EmptySearch : FactsBusiness()
}
