package com.jfcorugedo.udemy.javascriptalgorithmsanddatastructures;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Write a function which accepts a sorted array of integers and find the first pair where the sum is zero.
 *
 * The function should return another array with both values, or an empty array if there isn't any pair
 * of values that matches that position
 */
public class SumZero {

    @Test
    public void sumZero() {

        assertThat(solution(null)).isEmpty();
        assertThat(solution(new int[0])).isEmpty();
        assertThat(solution(new int[]{0})).isEmpty();
        assertThat(solution(new int[]{-1, 1})).contains(-1, 1);
        assertThat(solution(IntStream.range(-2, 3).toArray())).contains(-2, 2);
        assertThat(solution(new int[]{-10,-4,-3,0,1})).isEmpty();
        assertThat(solution(IntStream.range(-2, 30).toArray())).contains(-2, 2);
        assertThat(solution(IntStream.range(-20, 3).toArray())).contains(-2, 2);
    }

    /**
     * This solution has a quadratic complexity O(n**2)
     * @param values
     * @return
     */
    private int[] solution(int[] values) {
        if(values == null || values.length < 2) return new int[0];

        int[] R = new int[0];

        for(int i = 0 ; i < values.length && R.length == 0 ; i++) {
            for(int j = values.length-1 ; j > i && R.length == 0 ; j--) {
                if(values[i] + values[j] == 0){
                    R = new int[]{values[i], values[j]};
                }
            }
        }
        return R;
    }

    @Test
    public void sumZero2() {

        assertThat(solution2(null)).isEmpty();
        assertThat(solution2(new int[0])).isEmpty();
        assertThat(solution2(new int[]{0})).isEmpty();
        assertThat(solution2(new int[]{-1, 1})).contains(-1, 1);
        assertThat(solution2(IntStream.range(-2, 3).toArray())).contains(-2, 2);
        assertThat(solution2(new int[]{-10,-4,-3,0,1})).isEmpty();
        assertThat(solution2(IntStream.range(-2, 30).toArray())).contains(-2, 2);
        assertThat(solution2(IntStream.range(-20, 3).toArray())).contains(-2, 2);
    }

    /**
     * Now we are going to use multiple pointers pattern to solve the problem.
     *
     * This solution has a complexity of O(n)
     */
    private int[] solution2(int[] values) {
        if(values == null || values.length < 2) return new int[0];

        int[] R = new int[0];

        int left = 0;
        int right = values.length-1;

        while(left < right && R.length == 0) {
            int sum = values[left] + values[right];
            if(sum == 0) {
                R = new int[]{values[left], values[right]};
            } else if(sum > 0) {
                right--;
            } else {
                left++;
            }
        }
        return R;
    }
}
