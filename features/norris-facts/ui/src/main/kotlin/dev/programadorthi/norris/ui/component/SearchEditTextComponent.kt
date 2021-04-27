package dev.programadorthi.norris.ui.component

import android.view.inputmethod.EditorInfo
import android.widget.EditText

class SearchEditTextComponent(
    view: EditText,
    onDoSearch: (String) -> Unit
) {
    init {
        view.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                onDoSearch(view.text.toString())
            }
            return@setOnEditorActionListener false
        }
    }
}
