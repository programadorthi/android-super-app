package dev.programadorthi.norris.ui.test.component

import androidx.core.view.isVisible
import androidx.test.core.app.launchActivity
import dev.programadorthi.norris.ui.component.LoadingComponent
import dev.programadorthi.norris.ui.fake.EmptyActivityFake
import dev.programadorthi.shared.domain.UIState
import dev.programadorthi.shared.domain.flow.PropertyUIStateFlow
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LoadingComponentTest {
    private val uiState = PropertyUIStateFlow<Int>()

    @Test
    fun `should hide view when state is not loading`() {
        launchActivity<EmptyActivityFake>().onActivity { activity ->
            // Given
            LoadingComponent(
                uiState = uiState.stateFlow,
                view = activity.root
            )
            // When
            uiState.update(UIState.Idle)
            // Then
            assertThat(activity.root.isVisible).isFalse
        }
    }

    @Test
    fun `should show view when state is loading`() {
        launchActivity<EmptyActivityFake>().onActivity { activity ->
            // Given
            LoadingComponent(
                uiState = uiState.stateFlow,
                view = activity.root
            )
            // When
            uiState.update(UIState.Loading)
            // Then
            assertThat(activity.root.isVisible).isTrue
        }
    }
}
