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
    fun `should do nothing while state is not an error or business`() {
        launchActivity<EmptyActivityFake>().onActivity { activity ->
            // Given
            ErrorComponent(
                uiState = uiState.stateFlow,
                view = activity.root
            )
            // When
            uiState.update(UIState.Loading)
            // Then
            assertThat(ShadowToast.shownToastCount()).isZero
        }
    }

    @Test
    fun `should show business toast when has business UIState`() {
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
            assertThat(ShadowToast.shownToastCount()).isOne
            assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo(expectedMessage)
        }
    }

    @Test
    fun `should show error toast when has error UIState`() {
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
            assertThat(ShadowToast.shownToastCount()).isOne
            assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo(expectedMessage)
        }
    }
}
