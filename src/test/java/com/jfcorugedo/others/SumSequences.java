package com.jfcorugedo.others;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given a set of contiguous numbers starting at 1 and ending at n, write an algorithm that calculates the sum of that sequence
 *
 * For example:
 * Given [1, 2, 3, 4, 5, 6]
 * Then the sum is 21 (1 + 2 + 3 + 4 + 5 + 6 = 21)
 *
 */
public class SumSequences {

    @Test
    public void sumSequences() {

        assertThat(solution(IntStream.range(1, 3).toArray())).isEqualTo(3);
        assertThat(solution(IntStream.range(1, 7).toArray())).isEqualTo(21);
        assertThat(solution(IntStream.range(1, 101).toArray())).isEqualTo(5050);
    }

    private long solution(int[] numbers) {

        int sum = 0;
        for(int i = 0 ; i < numbers.length ; i++) {
            sum += numbers[i];
        }

        return sum;
    }

    @Test
    public void sumSequences2() {

        assertThat(solution2(IntStream.range(1, 3).toArray())).isEqualTo(3);
        assertThat(solution2(IntStream.range(1, 7).toArray())).isEqualTo(21);
        assertThat(solution2(IntStream.range(1, 101).toArray())).isEqualTo(5050);
    }

    /**
     * Using Zomg Wut approach:
     *
     * Given X as the sum of numbers from 1 to n we can define X as:
     *
     * X = 1 + 2 + 3 + 4 + ... + n-2 + n-1 + n
     *
     * So X + X will be:
     *
     * 1 + 2 + 3 + 4 + ... + n-2 + n-1 + n
     * +
     * 1 + 2 + 3 + 4 + ... + n-2 + n-1 + n
     *
     * Given that 1 + 2 + 3 + 4 + ... + n-2 + n-1 + n is equal to n + n-1 + n-2 ... + 4 + 3 + 2 + 1
     * Then we can say that:
     * X + X:
     *   1 + 2 + 3 + 4 + ... + n-2 + n-1 + n
     * +
     *   n + n-1 + n-2 ... + 4 + 3 + 2 + 1
     *   ----------------------------------
     *   n+1 + n+1 + n+1 + n+1 + ... + n+1
     *
     * So:
     *
     * X + X = n * (n+1)
     * 2X = n * (n+1)
     * X = n * (n+1) / 2
     *
     * @param numbers
     * @return
     */
    private long solution2(int[] numbers) {

        int n = numbers[numbers.length-1];

        return n * (n+1) / 2;
    }
}
