package com.github.fatihsokmen.connectionmonitor.hooks

import com.github.fatihsokmen.connectionmonitor.loggers.UrlLogger
import de.robv.android.xposed.XC_MethodHook.MethodHookParam
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class UrlConnectionHookTest {

    /**
     * Setup dependencies
     */
    private val logger = mockk<UrlLogger>(relaxed = true)
    private val handler = mockk<UrlConnectionHook.Handler> {
        every { beforeHookedMethod(any()) } answers { logger.log(URL_TO_TEST) }
    }
    private val param = mockk<MethodHookParam>(relaxed = true)
    private val facade = mockk<XposedHelpersFacade> {
        every {
            findAndHookConstructor(
                "java.net.URL",
                any(),
                String::class.java,
                handler
            )
        } answers {
            handler.beforeHookedMethod(param)
        }
    }

    /**
     * Subject to test
     */

    private val subject = UrlConnectionHook(facade, handler, ClassLoader.getSystemClassLoader())

    @Test
    fun `GIVEN UrlConnectionHook WHEN installed THEN url should be logged`() {
        subject.install()

        verify {
            logger.log(URL_TO_TEST)
        }
    }

    companion object {
        private const val URL_TO_TEST = "https://datatheorem.com"
    }
}