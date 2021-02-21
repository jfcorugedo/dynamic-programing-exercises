package com.jfcorugedo.udemy.javascriptalgorithmsanddatastructures;

import org.junit.Test;

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
    }

    private int[] solution(int[] values) {
        if(values == null || values.length < 2) return new int[0];

        int[] R = new int[0];

        for(int i = 0 ; i < values.length ; i++) {
            for(int j = values.length-1 ; j >= 0 ; j--) {
                if(values[i] + values[j] == 0){
                    R = new int[]{values[i], values[j]};
                }
            }
        }
        return R;
    }
}
