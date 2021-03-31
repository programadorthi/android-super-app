package dev.programadorthi.norris.domain.model

data class Fact(
    val id: String,
    val url: String,
    val value: String,
    val categories: List<String>
)
