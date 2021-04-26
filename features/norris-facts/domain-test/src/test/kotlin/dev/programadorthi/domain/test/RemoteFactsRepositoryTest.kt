package dev.programadorthi.domain.test

import dev.programadorthi.norris.domain.data.RemoteFactsRepository
import dev.programadorthi.norris.domain.data.remote.raw.FactRaw
import dev.programadorthi.norris.domain.data.remote.raw.FactsResponseRaw
import dev.programadorthi.norris.domain.data.remote.repository.RemoteFactsRepositoryFactory
import dev.programadorthi.norris.domain.fake.data.FactsServiceFake
import dev.programadorthi.norris.domain.model.Fact
import dev.programadorthi.shared.network.fake.NetworkManagerFake
import dev.programadorthi.shared.network.fake.RemoteMapperFake
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class RemoteFactsRepositoryTest {

    private val scope = TestCoroutineScope()
    private lateinit var factsMapper: RemoteMapperFake<FactsResponseRaw, List<Fact>>
    private lateinit var service: FactsServiceFake
    private lateinit var repository: RemoteFactsRepository

    @Before
    fun `before each test`() {
        factsMapper = RemoteMapperFake()
        service = FactsServiceFake()
        repository = RemoteFactsRepositoryFactory(
            factsMapper = factsMapper,
            factsService = service,
            networkManager = NetworkManagerFake(scope)
        )
    }

    @After
    fun `after each test`() {
        scope.cleanupTestCoroutines()
    }

    @Test
    fun `should get empty categories from server`() = scope.runBlockingTest {
        val expected = emptyList<String>()
        val result = repository.fetchCategories()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should get empty facts from server when it found nothing`() = scope.runBlockingTest {
        service.raw = FactsResponseRaw()
        factsMapper.mapper = { emptyList() }
        val expected = emptyList<Fact>()
        val result = repository.search(text = "alkjdj fajlkdflk ajsldfjlas d")
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should get categories from server`() = scope.runBlockingTest {
        val categories = Array(5) { index ->
            "Cat$index"
        }
        val expected = categories.toList()
        service.addCategories(*categories)
        val result = repository.fetchCategories()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should get facts from server when it found any`() = scope.runBlockingTest {
        val expected = listOf(
            Fact(
                id = "id",
                url = "url",
                value = "value",
                categories = emptyList()
            )
        )
        val factsRaw = expected.map { fact ->
            FactRaw(
                id = fact.id,
                url = fact.url,
                value = fact.value,
                categories = fact.categories
            )
        }
        service.raw = FactsResponseRaw(result = factsRaw)
        factsMapper.mapper = { expected }
        val result = repository.search(text = "alkjdjfajlkdflkajsldfjlasd")
        assertThat(result).isEqualTo(expected)
    }
}
