package com.jfcorugedo.codility;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * An array A consisting of N integers is given. Rotation of the array means that each element is shifted right by one index, and the last element of the array is moved to the first place. For example, the rotation of array A = [3, 8, 9, 7, 6] is [6, 3, 8, 9, 7] (elements are shifted right by one index and 6 is moved to the first place).
 *
 * The goal is to rotate array A K times; that is, each element of A will be shifted to the right K times.
 *
 * Write a function:
 *
 * class Solution { public int[] solution(int[] A, int K); }
 *
 * that, given an array A consisting of N integers and an integer K, returns the array A rotated K times.
 *
 * For example, given
 *
 *     A = [3, 8, 9, 7, 6]
 *     K = 3
 * the function should return [9, 7, 6, 3, 8]. Three rotations were made:
 *
 *     [3, 8, 9, 7, 6] -> [6, 3, 8, 9, 7]
 *     [6, 3, 8, 9, 7] -> [7, 6, 3, 8, 9]
 *     [7, 6, 3, 8, 9] -> [9, 7, 6, 3, 8]
 * For another example, given
 *
 *     A = [0, 0, 0]
 *     K = 1
 * the function should return [0, 0, 0]
 *
 * Given
 *
 *     A = [1, 2, 3, 4]
 *     K = 4
 * the function should return [1, 2, 3, 4]
 */
public class CyclicRotation {

    @Test
    public void rotate() {

        assertThat(solution(new int[]{3, 8, 9, 7, 6}, 3)).containsExactly(9, 7, 6, 3, 8);
    }

    public int[] solution(int[] A, int K) {
        List<Integer> numbers = Arrays.stream(A).mapToObj(Integer::valueOf).collect(Collectors.toList());
        Collections.rotate(numbers, K);
        return numbers.stream().mapToInt(Integer::intValue).toArray();
    }

    @Test
    public void rotate2() {

        assertThat(solution2(new int[]{3, 8, 9, 7, 6}, 3)).containsExactly(9, 7, 6, 3, 8);
        assertThat(solution2(new int[]{}, 3)).isEmpty();
    }

    public int[] solution2(int[] A, int K) {
        if(A.length == 0) return A;
        int[] res = new int[A.length];

        int distance = K >= A.length ? K%A.length : K;

        for(int i = 0 ; i < A.length ; i++) {
            if(i+distance < A.length){
                res[i+distance] = A[i];
            } else {
                res[(i+distance)-A.length] = A[i];
            }
        }
        return res;
    }
}
