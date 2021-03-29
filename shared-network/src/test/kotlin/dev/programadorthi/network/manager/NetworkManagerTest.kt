package dev.programadorthi.network.manager

import dev.programadorthi.network.exception.NetworkingError
import dev.programadorthi.network.exception.NetworkingErrorMapper
import dev.programadorthi.network.exception.NetworkingErrorMapperImpl
import dev.programadorthi.network.fake.ConnectionCheckFake
import dev.programadorthi.network.fake.CrashReportFake
import dev.programadorthi.network.fake.RemoteMapperFake
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

        networkingErrorMapper = NetworkingErrorMapperImpl(
            crashReport = crashReportFake
        )

        networkManager = DefaultNetworkManager(
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
                    networkManager.performAndDone {}
                }
            }.isEqualTo(NetworkingError.NoInternetConnection)
            assertThat(crashReportFake.cause).isEqualTo(null)

            assertThatThrownBy {
                runBlocking {
                    networkManager.performAndReturnsData {}
                }
            }.isEqualTo(NetworkingError.NoInternetConnection)
            assertThat(crashReportFake.cause).isEqualTo(null)

            assertThatThrownBy {
                runBlocking {
                    networkManager.performAndReturnsMappedData(RemoteMapperFake()) {
                        "some response body"
                    }
                }
            }.isEqualTo(NetworkingError.NoInternetConnection)
            assertThat(crashReportFake.cause).isEqualTo(null)
        }

    @Test
    fun `should has EssentialParamMissing error when is missing required fields in the server response data`() =
        testDispatcher.runBlockingTest {
            assertThatThrownBy {
                runBlocking {
                    networkManager.performAndReturnsMappedData(
                        RemoteMapperFake(throwException = true)
                    ) {
                        "some response body"
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
                    networkManager.performAndDone { throw completableError }
                }
            }.isInstanceOf(NetworkingError.InvalidDataFormat::class.java)
            assertThat(crashReportFake.cause).isEqualTo(completableError)

            val singleError = SerializationException("0")

            assertThatThrownBy {
                runBlocking {
                    networkManager.performAndReturnsData { throw singleError }
                }
            }.isInstanceOf(NetworkingError.InvalidDataFormat::class.java)
            assertThat(crashReportFake.cause).isEqualTo(singleError)

            val singleMappedError = SerializationException("class")

            assertThatThrownBy {
                runBlocking {
                    networkManager.performAndReturnsMappedData(RemoteMapperFake()) {
                        throw singleMappedError
                    }
                }
            }.isInstanceOf(NetworkingError.InvalidDataFormat::class.java)
            assertThat(crashReportFake.cause).isEqualTo(singleMappedError)
        }

    @Test
    fun `should has ConnectionTimeout error when there is a slow internet connection`() =
        testDispatcher.runBlockingTest {

            assertThatThrownBy {
                runBlocking {
                    networkManager.performAndDone { throw SocketTimeoutException() }
                }
            }.isInstanceOf(NetworkingError.ConnectionTimeout::class.java)
            assertThat(crashReportFake.cause).isNull()

            assertThatThrownBy {
                runBlocking {
                    networkManager.performAndReturnsData { throw SocketTimeoutException() }
                }
            }.isInstanceOf(NetworkingError.ConnectionTimeout::class.java)
            assertThat(crashReportFake.cause).isNull()

            assertThatThrownBy {
                runBlocking {
                    networkManager.performAndReturnsMappedData(RemoteMapperFake()) { throw SocketTimeoutException() }
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
                    networkManager.performAndDone { throw completableError }
                }
            }.isInstanceOf(NetworkingError.UnknownEndpoint::class.java)
            assertThat(crashReportFake.cause).isEqualTo(completableError)

            val singleError = UnknownHostException("two")

            assertThatThrownBy {
                runBlocking {
                    networkManager.performAndReturnsData { throw singleError }
                }
            }.isInstanceOf(NetworkingError.UnknownEndpoint::class.java)
            assertThat(crashReportFake.cause).isEqualTo(singleError)

            val singleMappedError = UnknownHostException("three")

            assertThatThrownBy {
                runBlocking {
                    networkManager.performAndReturnsMappedData(RemoteMapperFake()) {
                        throw singleMappedError
                    }
                }
            }.isInstanceOf(NetworkingError.UnknownEndpoint::class.java)
            assertThat(crashReportFake.cause).isEqualTo(singleMappedError)
        }

    @Test
    fun `should has UnknownNetworkException error when throw a generic error`() =
        testDispatcher.runBlockingTest {
            val completableError = IOException("one")

            assertThatThrownBy {
                runBlocking {
                    networkManager.performAndDone { throw completableError }
                }
            }.isInstanceOf(NetworkingError.UnknownNetworkException::class.java)
            assertThat(crashReportFake.cause).isEqualTo(completableError)

            val singleError = IOException("two")

            assertThatThrownBy {
                runBlocking {
                    networkManager.performAndReturnsData { throw singleError }
                }
            }.isInstanceOf(NetworkingError.UnknownNetworkException::class.java)
            assertThat(crashReportFake.cause).isEqualTo(singleError)

            val singleMappedError = IOException("three")

            assertThatThrownBy {
                runBlocking {
                    networkManager.performAndReturnsMappedData(RemoteMapperFake()) {
                        throw  singleMappedError
                    }
                }
            }.isInstanceOf(NetworkingError.UnknownNetworkException::class.java)
            assertThat(crashReportFake.cause).isEqualTo(singleMappedError)
        }
}
