package com.jfcorugedo.udemy.javascriptalgorithmsanddatastructures;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Write a function which accepts two arrays.
 * The function should return true if every value in the first array has its corresponding value squared in the second
 * array. The frequency of values should be the same.
 */
public class SquaredArray {

    @Test
    public void oneIsEmpty() {

        assertThat(solution(new long[0], new long[]{2, 4})).isFalse();
    }

    @Test
    public void differentSize() {

        assertThat(solution(new long[]{1, 2, 3}, new long[]{2, 4})).isFalse();
    }

    @Test
    public void notSquared() {

        assertThat(solution(new long[]{3}, new long[]{4})).isFalse();
    }

    @Test
    public void squaredArray() {

        assertThat(solution(new long[]{1, 2, 3}, new long[]{1, 4, 9})).isTrue();
    }

    @Test
    public void squaredArrayUnordered() {

        assertThat(solution(new long[]{1, 2, 3}, new long[]{4, 1, 9})).isTrue();
    }

    @Test
    public void bitInputs() {

        assertThat(solution(LongStream.range(1, 10000001).toArray(), LongStream.range(1, 10000001).map(i -> i*i).toArray())).isTrue();
    }

    private boolean solution(long[] first, long[] second) {
        if((first == null || first.length == 0) != (second == null || second.length == 0)) return false;

        if(first.length != second.length) return false;

        Arrays.sort(first);
        Arrays.sort(second);

        boolean same = true;
        for(int i = 0 ; i < first.length && same ; i++) {
            same = second[i] == (first[i]*first[i]);
        }

        return same;
    }
}
