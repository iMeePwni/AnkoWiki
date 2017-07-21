package com.imeepwni.android.ankowiki.mockito;

import org.junit.*;

import java.util.*;

import static org.mockito.Mockito.*;

/**
 * Create by guofeng on 2017/7/21.
 * 1. Let's verify some behaviour!
 * The following examples mock a List, because most people are familiar with the interface (such as the add(), get(), clear() methods).
 * In reality, please don't mock the List class. Use a real instance instead.
 */

public class ListBehaviourTest {

    @Test
    public void listTest() {
        //mock creation
        List mockedList = mock(List.class);

        //using mock object
        mockedList.add("one");
        mockedList.clear();

        //verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }
}
