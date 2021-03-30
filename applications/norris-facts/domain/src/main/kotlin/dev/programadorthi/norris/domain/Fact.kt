package dev.programadorthi.norris.domain

data class Fact(
    val id: String,
    val url: String,
    val value: String,
    val categories: List<String>
)
