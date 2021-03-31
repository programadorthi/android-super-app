package dev.programadorthi.norris.local

import dev.programadorthi.norris.data.local.LocalFactsRepository

interface LocalFactsRepository : LocalFactsRepository {
    companion object Instance {
        operator fun invoke(
            database: NorrisQueries
        ): LocalFactsRepository = LocalFactsRepositoryImpl(database)
    }
}
