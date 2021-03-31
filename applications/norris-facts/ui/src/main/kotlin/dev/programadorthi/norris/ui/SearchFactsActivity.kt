package dev.programadorthi.norris.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import dev.programadorthi.norris.ui.component.ChipsComponent
import dev.programadorthi.norris.ui.component.SearchEditTextComponent
import dev.programadorthi.norris.ui.databinding.ActivitySearchFactsBinding

class SearchFactsActivity : AppCompatActivity() {
    private lateinit var searchFactsViewModel: SearchFactsViewModel

    private val viewBinding by lazy {
        ActivitySearchFactsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        SearchEditTextComponent(viewBinding.searchFactsEditText, ::goToFactsList)
        ChipsComponent(
            uiState = searchFactsViewModel.categories(),
            view = viewBinding.searchFactsCategoriesChipGroup,
            onChipClicked = ::goToFactsList,
            hasChipsListener = { hasChips ->
                viewBinding.suggestionsContainer.isVisible = hasChips
            }
        )
        ChipsComponent(
            uiState = searchFactsViewModel.lastSearches(),
            view = viewBinding.searchFactsLastSearchesChipGroup,
            onChipClicked = ::goToFactsList,
            hasChipsListener = { hasChips ->
                viewBinding.lastSearchesContainer.isVisible = hasChips
            }
        )

        searchFactsViewModel.run {
            fetchCategories()
            fetchLastSearches()
        }
    }

    private fun goToFactsList(query: String) {
        val intent = Intent().apply {
            putExtra(FactsActivity.SEARCH_RESULT_EXTRA_KEY, query)
        }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
