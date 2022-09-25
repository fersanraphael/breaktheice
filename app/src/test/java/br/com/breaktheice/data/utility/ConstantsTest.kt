package br.com.breaktheice.data.utility

import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ConstantsTest {

    @Test
    fun `Assert that database name is not empty`() {
        assertTrue(DATABASE_NAME.isNotEmpty())
    }

    @Test
    fun `Assert that webservice base url is not empty`() {
        assertTrue(WEBSERVICE_BASEURL.isNotEmpty())
    }

    @Test
    fun `Assert that webservice path segment is not empty`() {
        assertTrue(WEBSERVICE_PATH_SEGMENT.isNotEmpty())
    }

    @Test
    fun `Assert that webservice endpoint activity is not empty`() {
        assertTrue(WEBSERVICE_ENDPOINT_ACTIVITY.isNotEmpty())
    }

    @Test
    fun `Assert that webservice query type is not empty`() {
        assertTrue(WEBSERVICE_QUERY_TYPE.isNotEmpty())
    }
}
