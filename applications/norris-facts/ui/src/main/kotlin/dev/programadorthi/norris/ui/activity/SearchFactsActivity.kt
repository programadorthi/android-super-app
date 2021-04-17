package dev.programadorthi.norris.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import dev.programadorthi.norris.domain.viewmodel.SearchFactsViewModel
import dev.programadorthi.norris.ui.component.ChipsComponent
import dev.programadorthi.norris.ui.component.SearchEditTextComponent
import dev.programadorthi.norris.ui.databinding.ActivitySearchFactsBinding
import dev.programadorthi.shared.ui.di.ext.viewModel
import dev.programadorthi.shared.ui.ext.runScoped
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI

class SearchFactsActivity : AppCompatActivity(), DIAware {
    override val di: DI by closestDI()

    private val searchFactsViewModel: SearchFactsViewModel by viewModel()
    private val viewBinding by lazy {
        ActivitySearchFactsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        SearchEditTextComponent(viewBinding.searchFactsEditText, ::goToFactsList)
        ChipsComponent(
            uiState = searchFactsViewModel.categories,
            view = viewBinding.searchFactsCategoriesChipGroup,
            onChipClicked = ::goToFactsList,
            hasChipsListener = { hasChips ->
                viewBinding.suggestionsContainer.isVisible = hasChips
            }
        )
        ChipsComponent(
            uiState = searchFactsViewModel.lastSearches,
            view = viewBinding.searchFactsLastSearchesChipGroup,
            onChipClicked = ::goToFactsList,
            hasChipsListener = { hasChips ->
                viewBinding.lastSearchesContainer.isVisible = hasChips
            }
        )

        runScoped {
            searchFactsViewModel.run {
                fetchCategories()
                fetchLastSearches()
            }
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
