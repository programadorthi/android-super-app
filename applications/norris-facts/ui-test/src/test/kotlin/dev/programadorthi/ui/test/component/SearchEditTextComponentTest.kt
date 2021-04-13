package dev.programadorthi.ui.test.component

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.test.core.app.launchActivity
import dev.programadorthi.norris.ui.component.SearchEditTextComponent
import dev.programadorthi.norris.ui.fake.EmptyActivityFake
import dev.programadorthi.norris.ui.fake.component.SearchEditTextComponentActionsFake
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SearchEditTextComponentTest {
    private lateinit var action: SearchEditTextComponentActionsFake

    @Before
    fun `before each test`() {
        action = SearchEditTextComponentActionsFake()
    }

    @Test
    fun `should do nothing when attach the EditText`() {
        launchActivity<EmptyActivityFake>().onActivity { activity ->
            // Given
            val editText = EditText(activity)
            SearchEditTextComponent(
                view = editText,
                onDoSearch = action::onSearch
            )
            // Then
            assertThat(action.term()).isEqualTo(null)
        }
    }

    @Test
    fun `should search empty term when click keyboard search`() {
        launchActivity<EmptyActivityFake>().onActivity { activity ->
            // Given
            val editText = EditText(activity)
            SearchEditTextComponent(
                view = editText,
                onDoSearch = action::onSearch
            )
            val expected = ""
            // When
            editText.onEditorAction(EditorInfo.IME_ACTION_SEARCH)
            // Then
            assertThat(action.term()).isEqualTo(expected)
        }
    }

    @Test
    fun `should search term when click keyboard search`() {
        launchActivity<EmptyActivityFake>().onActivity { activity ->
            // Given
            val editText = EditText(activity)
            SearchEditTextComponent(
                view = editText,
                onDoSearch = action::onSearch
            )
            val expected = "animal"
            // When
            editText.apply {
                setText(expected)
                onEditorAction(EditorInfo.IME_ACTION_SEARCH)
            }
            // Then
            assertThat(action.term()).isEqualTo(expected)
        }
    }
}
