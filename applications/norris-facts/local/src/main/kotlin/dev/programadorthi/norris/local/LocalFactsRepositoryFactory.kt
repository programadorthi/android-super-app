package dev.programadorthi.norris.local

import dev.programadorthi.norris.data.local.LocalFactsRepository
import dev.programadorthi.shared.database.NorrisQueries

object LocalFactsRepositoryFactory {
    operator fun invoke(
        database: NorrisQueries
    ): LocalFactsRepository = LocalFactsRepositoryImpl(database)
}
