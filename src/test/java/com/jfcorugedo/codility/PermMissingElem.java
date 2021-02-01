package com.jfcorugedo.codility;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * An array A consisting of N different integers is given. The array contains integers in the range [1..(N + 1)], which means that exactly one element is missing.
 *
 * Your goal is to find that missing element.
 *
 * Write a function:
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given an array A, returns the value of the missing element.
 *
 * For example, given array A such that:
 *
 *   A[0] = 2
 *   A[1] = 3
 *   A[2] = 1
 *   A[3] = 5
 * the function should return 4, as it is the missing element.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [0..100,000];
 * the elements of A are all distinct;
 * each element of array A is an integer within the range [1..(N + 1)].
 */
public class PermMissingElem {

    @Test
    public void permMissingElem() {

        assertThat(solution(new int[]{2, 3, 4})).isEqualTo(1);
        assertThat(solution(new int[]{2, 4, 3, 5, 6})).isEqualTo(1);
        assertThat(solution(new int[]{2, 3, 1, 5})).isEqualTo(4);
        assertThat(solution(IntStream.range(1, 1000000).toArray())).isEqualTo(1000000);
    }

    public int solution(int[] A) {

        Arrays.sort(A);

        if(A.length < 1) return 1;
        if(A[0] != 1) return 1;
        if(A.length == 1) return 2;

        int missing = -1;
        for(int i = 0 ; i < A.length-1 && missing == -1 ; i++) {
            if(A[i] != A[i+1] - 1) {
                missing = A[i]+1;
            }
        }

        if(missing == -1) return A[A.length-1] + 1;

        return missing;
    }
}
