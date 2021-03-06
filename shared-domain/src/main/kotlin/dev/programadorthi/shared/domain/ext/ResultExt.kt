package dev.programadorthi.shared.domain.ext

import dev.programadorthi.shared.domain.Result
import dev.programadorthi.shared.domain.UIState

fun <T, R> Result<T>.toUIState(
    businessMessage: String = "",
    failureMessage: String = "",
    successMapper: (T?) -> UIState.Success<R>
) = when {
    isBusiness -> UIState.Business(
        cause = businessOrNull(),
        message = businessMessage
    )
    isFailure -> UIState.Error(
        cause = exceptionOrNull(),
        message = failureMessage
    )
    else -> successMapper.invoke(getOrNull())
}
