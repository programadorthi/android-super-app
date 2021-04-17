package dev.programadorthi.norris.ui.fake.component

import dev.programadorthi.norris.domain.model.presentation.FactViewData

class SuccessComponentActionsFake {
    private var emptyDataSet: Boolean = false
    private var shared: FactViewData? = null

    fun emptyDataSet() = emptyDataSet
    fun shared() = shared

    fun onEmptyDataSet() {
        emptyDataSet = true
    }

    fun share(fact: FactViewData) {
        shared = fact
    }
}
