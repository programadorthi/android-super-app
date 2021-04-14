package dev.programadorthi.norris.ui.fake.component

class ChipsComponentActionsFake {
    private var chip: String? = null
    private var hasChips: Boolean = false

    fun chip(): String? = chip
    fun hasChips(): Boolean = hasChips

    fun onChipClicked(chip: String) {
        this.chip = chip
    }

    fun hasChipsListener(hasChips: Boolean) {
        this.hasChips = hasChips
    }
}
