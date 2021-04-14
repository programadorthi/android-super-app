package dev.programadorthi.ui.test.component

import androidx.core.view.get
import androidx.test.core.app.launchActivity
import com.google.android.material.chip.ChipGroup
import dev.programadorthi.norris.ui.component.ChipsComponent
import dev.programadorthi.norris.ui.fake.EmptyActivityFake
import dev.programadorthi.norris.ui.fake.component.ChipsComponentActionsFake
import dev.programadorthi.shared.ui.UIState
import dev.programadorthi.shared.ui.flow.PropertyUIStateFlow
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.random.Random

@RunWith(RobolectricTestRunner::class)
class ChipsComponentTest {
    private val random = Random.Default
    private val uiState = PropertyUIStateFlow<List<String>>()
    private lateinit var action: ChipsComponentActionsFake

    @Before
    fun `before each test`() {
        action = ChipsComponentActionsFake()
    }

    @Test
    fun `should hide all chips when state is not success`() {
        launchActivity<EmptyActivityFake>().onActivity { activity ->
            // Given
            val chipGroup = ChipGroup(activity)
            ChipsComponent(
                uiState = uiState.stateFlow,
                view = chipGroup,
                onChipClicked = action::onChipClicked,
                hasChipsListener = action::hasChipsListener
            )
            // When
            uiState.update(UIState.Loading)
            // Then
            assertThat(chipGroup.childCount).isZero
            assertThat(action.hasChips()).isFalse
        }
    }

    @Test
    fun `should hide all chips when success with empty list`() {
        launchActivity<EmptyActivityFake>().onActivity { activity ->
            // Given
            val chipGroup = ChipGroup(activity)
            ChipsComponent(
                uiState = uiState.stateFlow,
                view = chipGroup,
                onChipClicked = action::onChipClicked,
                hasChipsListener = action::hasChipsListener
            )
            // When
            uiState.update(UIState.Success(emptyList()))
            // Then
            assertThat(chipGroup.childCount).isZero
            assertThat(action.hasChips()).isFalse
        }
    }

    @Test
    fun `should populate chips when success with valid list`() {
        launchActivity<EmptyActivityFake>().onActivity { activity ->
            // Given
            val chipGroup = ChipGroup(activity)
            ChipsComponent(
                uiState = uiState.stateFlow,
                view = chipGroup,
                onChipClicked = action::onChipClicked,
                hasChipsListener = action::hasChipsListener
            )
            val chips = List(5) { index -> "chip$index" }
            // When
            uiState.update(UIState.Success(chips))
            // Then
            assertThat(chipGroup.childCount).isEqualTo(5)
            assertThat(action.hasChips()).isTrue
        }
    }

    @Test
    fun `should handle chip when one is clicked`() {
        launchActivity<EmptyActivityFake>().onActivity { activity ->
            // Given
            val chipGroup = ChipGroup(activity)
            ChipsComponent(
                uiState = uiState.stateFlow,
                view = chipGroup,
                onChipClicked = action::onChipClicked,
                hasChipsListener = action::hasChipsListener
            )
            val chips = List(5) { index -> "chip$index" }
            val chipIndex = random.nextInt(5)
            val expected = chips[chipIndex]
            // When
            uiState.update(UIState.Success(chips))
            chipGroup[chipIndex].performClick()
            // Then
            assertThat(chipGroup.childCount).isEqualTo(5)
            assertThat(action.hasChips()).isTrue
            assertThat(action.chip()).isEqualTo(expected)
        }
    }
}
