package dev.programadorthi.ui.test.component

import androidx.test.core.app.launchActivity
import dev.programadorthi.norris.ui.component.ErrorComponent
import dev.programadorthi.norris.ui.fake.EmptyActivityFake
import dev.programadorthi.shared.ui.UIState
import dev.programadorthi.shared.ui.flow.PropertyUIStateFlow
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowToast

@RunWith(RobolectricTestRunner::class)
class ErrorComponentTest {
    private val uiState = PropertyUIStateFlow<Int>()

    @Test
    fun shouldDoNothingWhileStateIsNotAnErrorOrBusiness() {
        launchActivity<EmptyActivityFake>().onActivity { activity ->
            // Given
            ErrorComponent(
                uiState = uiState.stateFlow,
                view = activity.root
            )
            // When
            uiState.update(UIState.Loading)
            // Then
            assertThat(ShadowToast.shownToastCount()).isEqualTo(0)
        }
    }

    @Test
    fun shouldShowBusinessToastWhenHasBusinessUIState() {
        launchActivity<EmptyActivityFake>().onActivity { activity ->
            // Given
            ErrorComponent(
                uiState = uiState.stateFlow,
                view = activity.root
            )
            val expectedMessage = "business message"
            // When
            uiState.update(UIState.Business(cause = null, message = expectedMessage))
            // Then
            assertThat(ShadowToast.shownToastCount()).isEqualTo(1)
            assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo(expectedMessage)
        }
    }

    @Test
    fun shouldShowErrorToastWhenHasErrorUIState() {
        launchActivity<EmptyActivityFake>().onActivity { activity ->
            // Given
            ErrorComponent(
                uiState = uiState.stateFlow,
                view = activity.root
            )
            val expectedMessage = "error message"
            // When
            uiState.update(UIState.Error(cause = null, message = expectedMessage))
            // Then
            assertThat(ShadowToast.shownToastCount()).isEqualTo(1)
            assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo(expectedMessage)
        }
    }
}
