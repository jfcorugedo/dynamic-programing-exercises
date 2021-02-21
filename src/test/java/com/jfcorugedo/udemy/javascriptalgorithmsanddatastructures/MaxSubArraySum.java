package com.jfcorugedo.udemy.javascriptalgorithmsanddatastructures;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Write a function which accepts an array of integers and a number n.
 * The function must calculate the maximum sum of n consecutive elements in the array.
 *
 * For example:
 *
 * Given [1, 2, 5, 2, 8, 1, 5] and n=2 it should return 10 (2+8)
 * Given [4, 2, 1, 6, 2] and n=4 it should return 13 (4 + 2+ 1+ 6)
 */
public class MaxSubArraySum {

    @Test
    public void maxSubArraySum() {

        assertThat(solution(null, 1)).isZero();
        assertThat(solution(new int[0], 1)).isZero();
        assertThat(solution(new int[]{1}, 0)).isZero();
        assertThat(solution(new int[]{1}, 1)).isOne();
        assertThat(solution(new int[]{1}, 2)).isOne();
        assertThat(solution(new int[]{1, 2, 3, 4}, 2)).isEqualTo(7);
        assertThat(solution(new int[]{1, 6, 3, 4}, 2)).isEqualTo(9);
        assertThat(solution(new int[]{1, 6, -3, 4}, 2)).isEqualTo(7);
        assertThat(solution(new int[]{1, 6, -3, 4}, 3)).isEqualTo(7);
    }

    /**
     * Given an array R[i] which values are the maximum sum of a sequence ending in position i.
     *
     * Given that the sequence must have a length of n, then we can define R[i] as:
     *
     * R[i] = numbers[i-1] + numbers[i-2] + ... + numbers[i-n]
     * R[i+1] = numbers[i] + numbers[i-1] + numbers[i-2] + ... + numbers[i-n+1] = numbers[i] + R[i] - numbers[i-n]
     *
     * So we can say that:
     *
     * R[i] = numbers[i] + R[i] - numbers[i-n]
     *
     * This is called Sliding Windows Pattern.
     *
     * Where the base case will be:
     * R[length-1] = sum{numbers[1] + numbers[2] + numbers[3] + ... + numbers[length-1]}
     *
     * The answer of the problem would be max{R[1], R[2]...R[i]}
     */
    private int solution(int[] numbers, int length) {
        if(numbers == null || numbers.length == 0 || length == 0) return 0;
        if(numbers.length <= length) return Arrays.stream(numbers).sum();

        int[] R = new int[numbers.length];

        //Build the base case
        int firstSum = 0;
        for(int i = 0 ; i < length ; i++) {
            firstSum += numbers[i];
        }
        R[length-1] = firstSum;

        //Now build the rest
        for(int i = length ; i < numbers.length ; i++) {
            R[i] = numbers[i] + R[i-1] - numbers[i - length];
        }


        return Arrays.stream(R).max().getAsInt();
    }
}
