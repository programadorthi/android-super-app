package dev.programadorthi.shared.network.manager

import dev.programadorthi.shared.domain.exception.NetworkingError
import dev.programadorthi.shared.domain.exception.NetworkingErrorMapper
import dev.programadorthi.shared.domain.fake.CrashReportFake
import dev.programadorthi.shared.domain.fake.ConnectionCheckFake
import dev.programadorthi.shared.network.fake.RemoteMapperFake
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.serialization.SerializationException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkManagerTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var connectionCheckFake: ConnectionCheckFake
    private lateinit var crashReportFake: CrashReportFake
    private lateinit var networkingErrorMapper: NetworkingErrorMapper
    private lateinit var networkManager: NetworkManager

    @Before
    fun `before each test`() {
        connectionCheckFake = ConnectionCheckFake()
        crashReportFake = CrashReportFake()
        networkingErrorMapper = NetworkingErrorMapper(
            crashReport = crashReportFake
        )

        networkManager = NetworkManager(
            connectionCheck = connectionCheckFake,
            networkingErrorMapper = networkingErrorMapper,
            ioDispatcher = testDispatcher
        )
    }

    @Test
    fun `should has NoInternetConnection error when there is no internet connection`() =
        testDispatcher.runBlockingTest {
            connectionCheckFake.hasConnection = false

            assertThatThrownBy {
                runBlocking {
                    networkManager.execute { }
                }
            }.isEqualTo(NetworkingError.NoInternetConnection)
            assertThat(crashReportFake.cause).isEqualTo(null)
        }

    @Test
    fun `should has EssentialParamMissing error when is missing required fields in the server response data`() =
        testDispatcher.runBlockingTest {
            assertThatThrownBy {
                runBlocking {
                    networkManager.execute {
                        throw NetworkingError.EssentialParamMissing("missing params", Unit)
                    }
                }
            }.isInstanceOf(NetworkingError.EssentialParamMissing::class.java)
            assertThat(crashReportFake.cause).isNotNull()
        }

    @Test
    fun `should has InvalidDataFormat error when the server response json data has invalid format`() =
        testDispatcher.runBlockingTest {
            val completableError = SerializationException("field")

            assertThatThrownBy {
                runBlocking {
                    networkManager.execute { throw completableError }
                }
            }.isInstanceOf(NetworkingError.InvalidDataFormat::class.java)
            assertThat(crashReportFake.cause).isEqualTo(completableError)
        }

    @Test
    fun `should has ConnectionTimeout error when there is a slow internet connection`() =
        testDispatcher.runBlockingTest {

            assertThatThrownBy {
                runBlocking {
                    networkManager.execute { throw SocketTimeoutException() }
                }
            }.isInstanceOf(NetworkingError.ConnectionTimeout::class.java)
            assertThat(crashReportFake.cause).isNull()
        }

    @Test
    fun `should has UnknownEndpoint error when the endpoint is unreachable`() =
        testDispatcher.runBlockingTest {
            val completableError = UnknownHostException("one")

            assertThatThrownBy {
                runBlocking {
                    networkManager.execute { throw completableError }
                }
            }.isInstanceOf(NetworkingError.UnknownEndpoint::class.java)
            assertThat(crashReportFake.cause).isEqualTo(completableError)
        }

    @Test
    fun `should has UnknownNetworkException error when throw a generic error`() =
        testDispatcher.runBlockingTest {
            val completableError = IOException("one")

            assertThatThrownBy {
                runBlocking {
                    networkManager.execute { throw completableError }
                }
            }.isInstanceOf(NetworkingError.UnknownNetworkException::class.java)
            assertThat(crashReportFake.cause).isEqualTo(completableError)
        }
}
