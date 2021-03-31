package dev.programadorthi.shared.domain.exception

import dev.programadorthi.shared.domain.fake.CrashReportFake
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.serialization.SerializationException
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkingErrorMapperTest {

    private lateinit var crashReportFake: CrashReportFake
    private lateinit var networkingErrorMapper: NetworkingErrorMapper

    @Before
    fun `before each test`() {
        crashReportFake = CrashReportFake()
        networkingErrorMapper = NetworkingErrorMapperImpl(crashReportFake)
    }

    @Test
    fun `should throw EssentialParamMissing when throwable is an EssentialParamMissing`() =
        runBlockingTest {
            val expected = NetworkingError.EssentialParamMissing("", 1)
            val exception = networkingErrorMapper.mapper(expected)
            assertThat(exception).isEqualTo(expected)
        }

    @Test
    fun `should throw InvalidDataFormat when throwable is a SerializationException`() =
        runBlockingTest {
            val expected = NetworkingError.InvalidDataFormat
            val exception = networkingErrorMapper.mapper(SerializationException("field"))
            assertThat(exception).isEqualTo(expected)
        }

    @Test
    fun `should throw ConnectionTimeout when throwable is a SocketTimeoutException`() =
        runBlockingTest {
            val expected = NetworkingError.ConnectionTimeout
            val exception = networkingErrorMapper.mapper(SocketTimeoutException("field"))
            assertThat(exception).isEqualTo(expected)
        }

    @Test
    fun `should throw UnknownEndpoint when throwable is an UnknownHostException`() =
        runBlockingTest {
            val throwException = UnknownHostException()
            val exception = networkingErrorMapper.mapper(throwException)
            assertThat(exception)
                .isInstanceOf(NetworkingError.UnknownEndpoint::class.java)
                .hasCause(throwException)
        }

    @Test
    fun `should throw UnknownNetworkException when any other exception`() = runBlockingTest {
        val throwException = IOException()
        val exception = networkingErrorMapper.mapper(throwException)
        assertThat(exception)
            .isInstanceOf(NetworkingError.UnknownNetworkException::class.java)
            .hasCause(throwException)
    }

    @Test
    fun `should report EssentialParamMissing when throwable is an EssentialParamMissing`() =
        runBlockingTest {
            val expected = NetworkingError.EssentialParamMissing("", 1)
            networkingErrorMapper.mapper(expected)
            assertThat(crashReportFake.cause).isEqualTo(expected)
        }

    @Test
    fun `should report InvalidDataFormat when throwable is a SerializationException`() =
        runBlockingTest {
            val expected = SerializationException("field")
            networkingErrorMapper.mapper(expected)
            assertThat(crashReportFake.cause).isEqualTo(expected)
        }

    @Test
    fun `should report UnknownEndpoint when throwable is an UnknownHostException`() =
        runBlockingTest {
            val expected = UnknownHostException()
            networkingErrorMapper.mapper(expected)
            assertThat(crashReportFake.cause).isEqualTo(expected)
        }

    @Test
    fun `should report UnknownNetworkException when throwable is any other exception`() =
        runBlockingTest {
            val expected = IOException()
            networkingErrorMapper.mapper(expected)
            assertThat(crashReportFake.cause).isEqualTo(expected)
        }
}
