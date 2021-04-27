package dev.programadorthi.ui.test.component

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import dev.programadorthi.norris.domain.model.presentation.FactViewData
import dev.programadorthi.norris.ui.component.SuccessComponent
import dev.programadorthi.norris.ui.fake.EmptyActivityFake
import dev.programadorthi.norris.ui.fake.component.SuccessComponentActionsFake
import dev.programadorthi.shared.domain.UIState
import dev.programadorthi.shared.domain.flow.PropertyUIStateFlow
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.random.Random

@RunWith(RobolectricTestRunner::class)
class SuccessComponentTest {
    private val random = Random.Default
    private val uiState = PropertyUIStateFlow<List<FactViewData>>()
    private lateinit var successComponentActionsFake: SuccessComponentActionsFake

    @Before
    fun `before each test`() {
        successComponentActionsFake = SuccessComponentActionsFake()
    }

    @Test
    fun `should do nothing while ui state is not success`() {
        launchActivity<EmptyActivityFake>().onActivity { activity ->
            // Given
            val recyclerView = inflateRecyclerView(activity)
            SuccessComponent(
                uiState = uiState.stateFlow,
                view = recyclerView,
                shareFact = successComponentActionsFake::share,
                onEmptyDataSet = successComponentActionsFake::onEmptyDataSet
            )
            // When
            uiState.update(UIState.Idle)
            // Then
            assertThat(recyclerView.adapter?.itemCount).isZero
            assertThat(successComponentActionsFake.emptyDataSet()).isFalse
            assertThat(successComponentActionsFake.shared()).isNull()
        }
    }

    @Test
    fun `should populate adapter when have a list of facts`() {
        launchActivity<EmptyActivityFake>().onActivity { activity ->
            // Given
            val recyclerView = inflateRecyclerView(activity)
            SuccessComponent(
                uiState = uiState.stateFlow,
                view = recyclerView,
                shareFact = successComponentActionsFake::share,
                onEmptyDataSet = successComponentActionsFake::onEmptyDataSet
            )
            val facts = List(5) { index ->
                FactViewData(
                    category = "cat$index",
                    style = index,
                    url = "url$index",
                    value = "value$index"
                )
            }
            // When
            uiState.update(UIState.Success(facts))
            // Then
            assertThat(recyclerView.adapter?.itemCount).isEqualTo(5)
            assertThat(successComponentActionsFake.emptyDataSet()).isFalse
            assertThat(successComponentActionsFake.shared()).isNull()
        }
    }

    @Test
    fun `should not populate adapter when facts list is empty`() {
        launchActivity<EmptyActivityFake>().onActivity { activity ->
            // Given
            val recyclerView = inflateRecyclerView(activity)
            SuccessComponent(
                uiState = uiState.stateFlow,
                view = recyclerView,
                shareFact = successComponentActionsFake::share,
                onEmptyDataSet = successComponentActionsFake::onEmptyDataSet
            )
            val facts = emptyList<FactViewData>()
            // When
            uiState.update(UIState.Success(facts))
            // Then
            assertThat(recyclerView.adapter?.itemCount).isZero
            assertThat(successComponentActionsFake.emptyDataSet()).isTrue
            assertThat(successComponentActionsFake.shared()).isNull()
        }
    }

    @Test
    fun `should clear adapter when switch from success to loading`() {
        launchActivity<EmptyActivityFake>().onActivity { activity ->
            // Given
            val recyclerView = inflateRecyclerView(activity)
            SuccessComponent(
                uiState = uiState.stateFlow,
                view = recyclerView,
                shareFact = successComponentActionsFake::share,
                onEmptyDataSet = successComponentActionsFake::onEmptyDataSet
            )
            val facts = List(5) { index ->
                FactViewData(
                    category = "cat$index",
                    style = index,
                    url = "url$index",
                    value = "value$index"
                )
            }
            // When
            uiState.update(UIState.Success(facts))
            // Then
            assertThat(recyclerView.adapter?.itemCount).isEqualTo(5)
            assertThat(successComponentActionsFake.emptyDataSet()).isFalse
            assertThat(successComponentActionsFake.shared()).isNull()
            // When
            uiState.update(UIState.Loading)
            // Then
            assertThat(recyclerView.adapter?.itemCount).isZero
            assertThat(successComponentActionsFake.emptyDataSet()).isFalse
            assertThat(successComponentActionsFake.shared()).isNull()
        }
    }

    @Ignore("How to force recycler view draw items to click in a holder after?")
    @Test
    fun `should share fact when click in one to share`() {
        launchActivity<EmptyActivityFake>().onActivity { activity ->
            // Given
            val recyclerView = inflateRecyclerView(activity)
            SuccessComponent(
                uiState = uiState.stateFlow,
                view = recyclerView,
                shareFact = successComponentActionsFake::share,
                onEmptyDataSet = successComponentActionsFake::onEmptyDataSet
            )
            val facts = List(5) { index ->
                FactViewData(
                    category = "cat$index",
                    style = index,
                    url = "url$index",
                    value = "value$index"
                )
            }
            val factIndex = random.nextInt(facts.size)
            val expected = facts[factIndex]
            // When
            uiState.update(UIState.Success(facts))
            // Workaround to force recyclerview adapter to create and bind views
            recyclerView.apply {
                measure(0, 0)
                layout(0, 0, activity.window.decorView.width, activity.window.decorView.height)
            }
            recyclerView.findViewHolderForAdapterPosition(factIndex)
                ?.itemView
                ?.performClick()
                ?: throw IllegalStateException(">>>> null holder")
            // Then
            assertThat(recyclerView.adapter?.itemCount).isEqualTo(5)
            assertThat(successComponentActionsFake.emptyDataSet()).isFalse
            assertThat(successComponentActionsFake.shared()).isEqualTo(expected)
        }
    }

    private fun inflateRecyclerView(context: Context) = RecyclerView(context).apply {
        layoutManager = LinearLayoutManager(context)
    }
}
