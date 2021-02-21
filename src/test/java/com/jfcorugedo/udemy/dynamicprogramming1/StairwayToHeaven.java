package com.jfcorugedo.udemy.dynamicprogramming1;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You want to reach heaven that is at the top of this staircase.
 * The staircase has n steps. And at each step you can climb either 1 or 2 steps further.
 * In how many ways can you reach heaven?
 */
public class StairwayToHeaven {

    @Test
    public void stairwayToHeaven() {

        assertThat(solution(0)).isOne();
        assertThat(solution(1)).isOne();
        assertThat(solution(2)).isEqualTo(2);
        assertThat(solution(3)).isEqualTo(3);
        assertThat(solution(4)).isEqualTo(5);
        assertThat(solution(5)).isEqualTo(8);
        assertThat(solution(6)).isEqualTo(13);
    }

    /**
     * Given an array R[n] where each position is the number of ways to reach step 'n'.
     *
     * Given any step n, there is always two different ways of reach this step:
     * - You are at step n-1 and climb 1 step
     * - You are at step n-2 and climb 2 steps
     *
     * So we can define R[n] as the sum of the ways of climbing to n-1 plus the ways of climbing to n-2:
     *
     * R[n] = R[n-1] + R[n-2]
     *
     * There are two special cases:
     * R[0] = 0 (if there are no steps, there is only one way of climbing the stairs)
     * R[1] = 1 (if there is only one step, then there is only one way of climbing the stair)
     */
    private int solution(int steps) {

        int[] R = new int[steps+2];
        R[0] = 1;
        R[1] = 1;

        for(int i = 2 ; i <= steps ; i++) {
            R[i] = R[i-1] + R[i-2];
        }

        return R[steps];
    }
}
