package com.afedare.decagontest.socks;

import junit.framework.TestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SocksProblemTest {
    @Test
    public void testSamples() {
        assertThat(SocksProblem.noOfWashes(0, new int[]{1, 2, 1, 1}, new int[]{1, 4, 3, 2, 4}), is(1));
        assertThat(SocksProblem.noOfWashes(100, new int[]{1, 2, 1, 1}, new int[]{1, 4, 3, 2, 4}), is(4));
        assertThat(SocksProblem.noOfWashes(2, new int[]{1, 2, 1, 1}, new int[]{1, 4, 3, 2, 4}), is(3));
    }

    @Test
    public void testDifferentSocks() {
        assertThat(SocksProblem.noOfWashes(0, new int[]{1, 2, 3}, new int[]{1 }), is(0));
        assertThat(SocksProblem.noOfWashes(2, new int[]{1, 2, 3}, new int[]{ 2 }), is(1));
    }

    @Test
    public void testPicksSocks() {
        assertThat(SocksProblem.noOfWashes(3, new int[]{1, 2, 2, 4, 4, 2}, new int[]{1, 2, 3, 6}), is(4));
        assertThat(SocksProblem.noOfWashes(4, new int[]{1, 2, 2, 4, 4, 2}, new int[]{1, 2, 3, 6}), is(4));
        assertThat(SocksProblem.noOfWashes(5, new int[]{1, 2, 2, 4, 4, 2}, new int[]{1, 2, 3, 6}), is(4));
    }
}