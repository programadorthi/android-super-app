package dev.programadorthi.norris.local

import dev.programadorthi.norris.data.local.LocalFactsRepository

object LocalFactsRepositoryFactory {
    operator fun invoke(
        database: NorrisQueries
    ): LocalFactsRepository = LocalFactsRepositoryImpl(database)
}
