package com.jfcorugedo.udemy.dynamicprogramming1;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Fibonacci numbers are defined by these equations:
 *
 * f(0) = 0
 * f(1) = 1
 * f(n) = f(n-1) + f(n-2)
 *
 * Build an algorithm that calculates the nth number in the fibonacci series.
 *
 * For example:
 *
 * f(3) = 2 (1 + 1)
 * f(8) = 21
 */
public class FibonacciSeries {

    @Test
    public void fibonacciSeries() {

        assertThat(solution(0)).isEqualTo(0);
        assertThat(solution(1)).isEqualTo(1);
        assertThat(solution(2)).isEqualTo(1);
        assertThat(solution(3)).isEqualTo(2);
        assertThat(solution(7)).isEqualTo(13);
        assertThat(solution(43)).isEqualTo(433494437);
    }

    /**
     * This solution is not very good in terms of performance. It has quadratic complexity: O(n**2)
     */
    private int solution(int nth) {
        if(nth == 0) return 0;
        if(nth == 1) return 1;

        return solution(nth-1) + solution(nth-2);
    }

    @Test
    public void fibonacciSeries2() {

        assertThat(solution2(0)).isEqualTo(0);
        assertThat(solution2(1)).isEqualTo(1);
        assertThat(solution2(2)).isEqualTo(1);
        assertThat(solution2(3)).isEqualTo(2);
        assertThat(solution2(7)).isEqualTo(13);
        assertThat(solution2(43)).isEqualTo(433494437);
    }

    /**
     * Using dynamic programming we can reduce the complexity of this algorithm to O(n)
     * Given an array of values R[n], which values are the fibonacci number of n position.
     * So
     * R[5] = 5
     * R[8] = 21
     * ...
     * Given any value of n, we can express R[n] as:
     * R[n] = R[n-1] + R[n-2]
     *
     * With two exceptions:
     * R[0] = 0 and R[1] = 1
     *
     */
    private int solution2(int nth) {

        if(nth == 0) return 0;

        int R[] = new int[nth+1];
        R[0] = 0;
        R[1] = 1;

        for(int i = 2 ; i <= nth ; i++) {
            R[i] = R[i-1] + R[i-2];
        }

        return R[nth];
    }
}
