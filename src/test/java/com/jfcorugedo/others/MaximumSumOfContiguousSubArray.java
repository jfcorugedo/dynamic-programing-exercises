package com.jfcorugedo.others;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Find the contiguous (without elements in between) sub array within an array (containing at least one number) which has
 * the largest sum.
 *
 * Example:
 *
 * Given: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 *
 * Solution: [4, -1, 2, 1] which sums 6
 *
 */
public class MaximumSumOfContiguousSubArray {

    @Test
    public void maximumSumOfContiguousSubArray() {

        assertThat(solution(new int[0])).isZero();
        assertThat(solution(new int[]{1})).isEqualTo(1);
        assertThat(solution(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})).isEqualTo(6); //[4, -1, 2, 1]
        assertThat(solution(new int[]{-2, 1, -3, 4, -1, 2, 1, -1, 4, 3, -1, 3, -2, 5})).isEqualTo(17); //[4, -1, 2, 1, -1, 4, 3, -1, 3, -2, 5]
    }

    private int solution(int[] x) {
        if(x.length == 0) return 0;

        /*
        Let's use a two dimension dynamic programming approach. R[i][j] will contains the sum of elements from
        position i to position j.

        So R[3][6] will contains the sum of elements from position 3 to position 4.

        The solution will be the max of all those numbers
         */
        int R[][] = new int[x.length + 1][x.length + 1];

        int maxValue = 0;
        int from = 0;
        int to = 0;
        for(int i = 1 ; i <= x.length ; i++) {
            for(int j = i ; j <= x.length ; j++) {
                R[i][j] = R[i][j-1] + x[j-1];
                if(R[i][j] > maxValue) {
                    maxValue = R[i][j];
                    from = i;
                    to = j;
                }
            }
        }

        System.out.println(String.format("maximum sub array: %s", Arrays.toString(Arrays.copyOfRange(x, from-1, to))));
        return maxValue;
    }
}
