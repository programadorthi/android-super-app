package dev.programadorthi.norris.domain.data.local

import dev.programadorthi.norris.domain.data.LocalFactsRepository
import dev.programadorthi.shared.database.NorrisQueries

object LocalFactsRepositoryFactory {
    operator fun invoke(
        database: NorrisQueries
    ): LocalFactsRepository = LocalFactsRepositoryImpl(database)
}
