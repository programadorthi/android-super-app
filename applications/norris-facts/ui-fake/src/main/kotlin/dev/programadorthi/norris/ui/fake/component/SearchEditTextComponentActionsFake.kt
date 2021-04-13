package dev.programadorthi.norris.ui.fake.component

class SearchEditTextComponentActionsFake {
    private var term: String? = null

    fun term(): String? = term

    fun onSearch(term: String) {
        this.term = term
    }
}
