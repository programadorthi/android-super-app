package dev.programadorthi.norris.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import dev.programadorthi.norris.domain.model.presentation.FactViewData
import dev.programadorthi.norris.domain.viewmodel.FactsViewModel
import dev.programadorthi.norris.ui.R
import dev.programadorthi.norris.ui.component.ErrorComponent
import dev.programadorthi.norris.ui.component.LoadingComponent
import dev.programadorthi.norris.ui.component.SuccessComponent
import dev.programadorthi.norris.ui.databinding.ActivityFactsBinding
import dev.programadorthi.shared.ui.ext.runScoped
import javax.inject.Inject

@AndroidEntryPoint
class FactsActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityFactsBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var factsViewModel: FactsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // TODO: using facts container but could be a Error Custom View managed by ErrorComponent
        ErrorComponent(
            uiState = factsViewModel.facts,
            view = binding.factsContainer
        )
        LoadingComponent(
            uiState = factsViewModel.facts,
            view = binding.factsProgressBar
        )
        SuccessComponent(
            uiState = factsViewModel.facts,
            view = binding.factsRecyclerView,
            shareFact = ::shareFact,
            onEmptyDataSet = {
                Toast.makeText(
                    this,
                    R.string.activity_facts_empty_search_result,
                    Toast.LENGTH_LONG
                ).show()
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.chuck_norris_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuSearch) {
            startSearch()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SEARCH_FACT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val query = data?.getStringExtra(SEARCH_RESULT_EXTRA_KEY) ?: EMPTY_TEXT
            runScoped {
                factsViewModel.search(query)
            }
        }
    }

    private fun shareFact(factViewData: FactViewData) {
        val shareFactIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, factViewData.url)
            type = SHARE_FACT_CONTENT_TYPE
        }
        startActivity(
            Intent.createChooser(shareFactIntent, "Share this fact")
        )
    }

    private fun startSearch() {
        val intent = Intent(this, SearchFactsActivity::class.java)
        startActivityForResult(intent, SEARCH_FACT_REQUEST_CODE)
    }

    companion object {
        private const val EMPTY_TEXT = ""
        private const val SHARE_FACT_CONTENT_TYPE = "text/plain"
        private const val SEARCH_FACT_REQUEST_CODE = 999

        const val SEARCH_RESULT_EXTRA_KEY = "search_result"
    }
}
