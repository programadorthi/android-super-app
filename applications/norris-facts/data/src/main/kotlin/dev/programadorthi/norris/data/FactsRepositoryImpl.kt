package dev.programadorthi.norris.data

import dev.programadorthi.norris.domain.model.Category
import dev.programadorthi.norris.domain.model.Fact
import dev.programadorthi.norris.local.LastSearch
import dev.programadorthi.norris.local.LocalFactsRepository
import dev.programadorthi.norris.remote.repository.RemoteFactsRepository
import dev.programadorthi.shared.domain.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import dev.programadorthi.norris.domain.model.LastSearch as DomainLastSearch

internal class FactsRepositoryImpl(
    private val localFactsRepository: LocalFactsRepository,
    private val remoteFactsRepository: RemoteFactsRepository,
    private val ioDispatcher: CoroutineDispatcher
) : FactsRepository {

    override suspend fun fetchCategories(
        limit: Int,
        shuffle: Boolean
    ): Result<List<Category>> = withContext(ioDispatcher) {
        val categories = mutableListOf<Category>()
        categories += try {
            localFactsRepository
                .getCategories()
                .map { category -> Category(name = category) }
        } catch (ex: Throwable) {
            return@withContext Result.failure(ex)
        }
        if (categories.isEmpty()) {
            categories += try {
                remoteFactsRepository
                    .fetchCategories()
                    .map { name -> Category(name = name) }
            } catch (ex: Throwable) {
                return@withContext Result.failure(ex)
            }
        }
        val result = (if (shuffle) categories.shuffled() else categories).take(limit)
        return@withContext Result.success(result)
    }

    override suspend fun getLastSearches(): Result<List<DomainLastSearch>> =
        withContext(ioDispatcher) {
            try {
                Result.success(
                    localFactsRepository
                        .getLastSearches()
                        .map { term -> DomainLastSearch(term = term) }
                )
            } catch (ex: Throwable) {
                Result.failure(ex)
            }
        }

    override suspend fun doSearch(text: String): Result<List<Fact>> =
        withContext(ioDispatcher) {
            try {
                val result = remoteFactsRepository.search(text)
                if (result.isNotEmpty()) {
                    localFactsRepository.saveNewSearch(LastSearch(term = text))
                }
                Result.success(result)
            } catch (ex: Throwable) {
                Result.failure(ex)
            }
        }
}
