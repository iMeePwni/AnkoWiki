package com.imeepwni.android.ankowiki.mockito

import org.junit.*
import org.mockito.Mockito.*
import java.util.*
import org.mockito.Mockito.`when`



/**
 * Create by guofeng on 2017/7/27.
 */
class MockitoTest {

    @Test
    fun stubbingSpy() {
        val linkedList = LinkedList<String>()
        val spy = spy(linkedList)

        //Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
        `when`(spy[0]).thenReturn("foo")
        println(spy[0])

        //You have to use doReturn() for stubbing
        doReturn("foo").`when`(spy)[0]
        println(spy[0])
    }
}