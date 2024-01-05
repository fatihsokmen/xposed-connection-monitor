package com.github.fatihsokmen.connectionmonitor.hooks

import com.github.fatihsokmen.connectionmonitor.loggers.UrlLogger
import de.robv.android.xposed.XC_MethodHook
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class OkHttpConnectionHookTest {

    /**
     * Setup dependencies
     */
    private val logger = mockk<UrlLogger>(relaxed = true)
    private val handler = mockk<OkHttpConnectionHook.Handler> {
        every { beforeHookedMethod(any()) } answers { logger.log(URL_TO_TEST) }
    }
    private val param = mockk<XC_MethodHook.MethodHookParam>(relaxed = true)
    private val cls =  Any::class.java
    private val facade = mockk<XposedHelpersFacade> {
        every {
            findClassIfExists(
                "okhttp3.internal.connection.Exchange",
                ClassLoader.getSystemClassLoader()
            )
        } returns cls
        every {
            findAndHookMethod(
                cls,
                "writeRequestHeaders",
                "okhttp3.Request",
                handler
            )
        } answers {
            handler.beforeHookedMethod(param)
        }
    }


    /**
     * Subject to test
     */

    private val subject = OkHttpConnectionHook(facade, handler, ClassLoader.getSystemClassLoader())

    @Test
    fun `GIVEN X WHEN Y THEN Z`() {
        subject.install()

        verify {
            logger.log(URL_TO_TEST)
        }
    }

    companion object {
        private const val URL_TO_TEST = "https://datatheorem.com"
    }
}