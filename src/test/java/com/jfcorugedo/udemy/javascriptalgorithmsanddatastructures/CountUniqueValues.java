package com.jfcorugedo.udemy.javascriptalgorithmsanddatastructures;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Implement a function which accepts a sorted array and counts the unique values in the array.
 *
 * Take into account that there can be negative numbers in the array and that each element can be repeated more than once
 *
 * Ej:
 * Given [1,2,3] the output should be 3
 * Given [1,1,1,2,2,3] the output should be also 3
 */
public class CountUniqueValues {

    @Test
    public void countUniqueValues() {

        assertThat(solution(null)).isZero();
        assertThat(solution(new int[0])).isZero();
        assertThat(solution(new int[]{1})).isOne();
        assertThat(solution(new int[]{1, 1})).isOne();
        assertThat(solution(new int[]{1, 2})).isEqualTo(2);
        assertThat(solution(new int[]{1, 2, 3})).isEqualTo(3);
        assertThat(solution(new int[]{1, 2, 3, 3})).isEqualTo(3);
        assertThat(solution(IntStream.range(1, 101).toArray())).isEqualTo(100);
        assertThat(solution(new int[]{1, 1, 2})).isEqualTo(2);
        assertThat(solution(new int[]{1, 1, 1, 2})).isEqualTo(2);
        assertThat(solution(new int[]{1, 2, 2})).isEqualTo(2);
        assertThat(solution(new int[]{1, 2, 2, 2})).isEqualTo(2);
        assertThat(solution(new int[]{-2, -1, -1, 0, 1})).isEqualTo(4);
    }

    private int solution(int[] numbers) {

        if(numbers == null || numbers.length == 0) return 0;
        if(numbers.length == 1) return 1;

        int total = 1;
        int lastUnique = 0;
        for(int current = 1 ; current < numbers.length ; current++) {
            if(numbers[current] != numbers[lastUnique]) {
                total++;
                lastUnique = current;
            }
        }

        return total;
    }
}
