package dev.programadorthi.ui.test.component

import androidx.core.view.isVisible
import androidx.test.core.app.launchActivity
import dev.programadorthi.norris.ui.component.LoadingComponent
import dev.programadorthi.norris.ui.fake.EmptyActivityFake
import dev.programadorthi.shared.ui.UIState
import dev.programadorthi.shared.ui.flow.PropertyStateFlow
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LoadingComponentTest {
    private val uiState = PropertyStateFlow<UIState<Int>>()

    @Test
    fun shouldHideViewWhenStateIsNotLoading() {
        launchActivity<EmptyActivityFake>().onActivity { activity ->
            LoadingComponent(
                uiState = uiState.stateFlow,
                view = activity.root
            )
            uiState.update(UIState.Idle)
            assertThat(activity.root.isVisible).isEqualTo(false)
        }
    }

    @Test
    fun shouldShowViewWhenStateIsLoading() {
        launchActivity<EmptyActivityFake>().onActivity { activity ->
            LoadingComponent(
                uiState = uiState.stateFlow,
                view = activity.root
            )
            uiState.update(UIState.Loading)
            assertThat(activity.root.isVisible).isEqualTo(true)
        }
    }
}
