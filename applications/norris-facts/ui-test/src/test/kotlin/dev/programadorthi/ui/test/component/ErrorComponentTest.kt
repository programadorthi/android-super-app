package dev.programadorthi.ui.test.component

import androidx.test.core.app.launchActivity
import dev.programadorthi.norris.ui.component.ErrorComponent
import dev.programadorthi.norris.ui.fake.EmptyActivityFake
import dev.programadorthi.shared.ui.UIState
import dev.programadorthi.shared.ui.flow.PropertyStateFlow
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowToast

@RunWith(RobolectricTestRunner::class)
class ErrorComponentTest {
    private val uiState = PropertyStateFlow<UIState<Int>>()

    @Test
    fun shouldDoNothingWhileStateIsNotAnErrorOrBusiness() {
        launchActivity<EmptyActivityFake>().onActivity { activity ->
            ErrorComponent(
                uiState = uiState.stateFlow,
                view = activity.root
            )
            uiState.update(UIState.Loading)
            assertThat(ShadowToast.shownToastCount()).isEqualTo(0)
        }
    }

    @Test
    fun shouldShowBusinessToastWhenHasBusinessUIState() {
        launchActivity<EmptyActivityFake>().onActivity { activity ->
            ErrorComponent(
                uiState = uiState.stateFlow,
                view = activity.root
            )
            val expectedMessage = "business message"
            uiState.update(UIState.Business(cause = null, message = expectedMessage))
            assertThat(ShadowToast.shownToastCount()).isEqualTo(1)
            assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo(expectedMessage)
        }
    }

    @Test
    fun shouldShowErrorToastWhenHasErrorUIState() {
        launchActivity<EmptyActivityFake>().onActivity { activity ->
            ErrorComponent(
                uiState = uiState.stateFlow,
                view = activity.root
            )
            val expectedMessage = "error message"
            uiState.update(UIState.Error(cause = null, message = expectedMessage))
            assertThat(ShadowToast.shownToastCount()).isEqualTo(1)
            assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo(expectedMessage)
        }
    }
}
