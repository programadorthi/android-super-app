package dev.programadorthi.norris.ui.fake.component

import dev.programadorthi.norris.ui.model.FactViewData

class SuccessComponentActionsFake {
    private var emptyDataSet: Boolean = false
    private var shared: FactViewData? = null

    fun emptyDataSet() = emptyDataSet
    fun shared(): FactViewData? = shared

    fun onEmptyDataSet() {
        emptyDataSet = true
    }

    fun share(fact: FactViewData) {
        shared = fact
    }
}
