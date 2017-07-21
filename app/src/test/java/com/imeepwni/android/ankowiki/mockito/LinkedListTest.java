package com.imeepwni.android.ankowiki.mockito;

import org.junit.*;
import org.mockito.*;

import java.util.*;

import static org.mockito.Mockito.*;

/**
 * Create by guofeng on 2017/7/21.
 */

public class LinkedListTest {

    LinkedList mockedList;

    @Before
    public void initLinkedList() {
        mockedList = mock(LinkedList.class);
    }

    @Test
    public void linkedList() throws Exception {
        //stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        //following prints "first"
        System.out.println(mockedList.get(0));

        //following throws runtime exception
//        System.out.println(mockedList.get(1));

        //following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));

        //Although it is possible to verify a stubbed invocation, usually it's just redundant
        //If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
        //If your code doesn't care what get(0) returns, then it should not be stubbed. Not convinced? See here.
        verify(mockedList).get(0);
    }

    @Test
    public void argumentMatcher() throws Exception {
        //stubbing using built-in anyInt() argument matcher
        when(mockedList.get(anyInt())).thenReturn("element");

        //stubbing using custom matcher (let's say isValid() returns your own matcher implementation):
        when(mockedList.contains(anyString())).thenReturn(true);

        //following prints "element"
        System.out.println(mockedList.get(999));

        //you can also verify using an argument matcher
        verify(mockedList).get(anyInt());

    }

    @Test
    public void timesAtLeast() throws Exception {
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        //following two verifications work exactly the same - times(1) is used by default
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        //exact number of invocations verification
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        //verification using never(). never() is an alias to times(0)
        verify(mockedList, never()).add("never happened");

        //verification using atLeast()/atMost()
        verify(mockedList, atLeastOnce()).add("three times");
        verify(mockedList, atLeast(2)).add("three times");
        verify(mockedList, atMost(5)).add("three times");
    }

    @Test
    public void throwExceptionTest() throws Exception {
        doThrow(new RuntimeException()).when(mockedList).clear();
        //following throws RuntimeException:
//        mockedList.clear();
    }

    @Test
    public void verificationInOrder() throws Exception {
        // A. Single mock whose methods must be invoked in a particular order
        List singleMock = mock(List.class);

        //using a single mock
        singleMock.add("was added first");
        singleMock.add("was added second");

        //create an inOrder verifier for a single mock
        InOrder inOrder = inOrder(singleMock);

        //following will make sure that add is first called with "was added first, then with "was added second"
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        // B. Multiple mocks that must be used in a particular order
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        //using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");

        //create inOrder object passing any mocks that need to be verified in order
        InOrder inOrder1 = inOrder(firstMock, secondMock);

        //following will make sure that firstMock was called before secondMock
        inOrder1.verify(firstMock).add("was called first");
        inOrder1.verify(secondMock).add("was called second");

        // Oh, and A + B can be mixed together at will
    }

    @Test
    public void noInteraction() throws Exception {
        List mockOne = mock(List.class);
        List mockTwo = mock(List.class);
        List mockThree = mock(List.class);

        //using mocks - only mockOne is interacted
        mockOne.add("one");

        //ordinary verification
        verify(mockOne).add("one");

        //verify that method was never called on a mock
        verify(mockOne, never()).add("two");
        //verify that other mocks were not interacted
        verifyZeroInteractions(mockTwo, mockThree);
    }

    @Test
    public void findingRedundantInvocations() throws Exception {
        //using mocks
        mockedList.add("one");
        mockedList.add("two");

        verify(mockedList).add("one");

        //following verification will fail
//        verifyNoMoreInteractions(mockedList);
    }

    @Test
    public void stubbingConsecutiveCalls() throws Exception {
        when(mockedList.get(anyInt()))
                .thenReturn("111")
                .thenReturn("222");

        System.out.println(mockedList.get(0));
        System.out.println(mockedList.get(0));
    }
}
