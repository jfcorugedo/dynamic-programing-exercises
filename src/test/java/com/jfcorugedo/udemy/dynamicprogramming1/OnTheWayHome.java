package com.jfcorugedo.udemy.dynamicprogramming1;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are located at the top-left corner of a MxN grid.
 * You can only move either down or right at any point of time.
 * Your home is located at the bottom right corner of this grid.
 *
 * In how many different ways can you reach your home?
 *
 * For example, given a 2x2 grid, there are 2 different ways:
 * - Go right and then go down
 * - Go down and then go right
 *
 */
public class OnTheWayHome {

    @Test
    public void onTheWayHome() {

        assertThat(solution(0, 0)).isEqualTo(1);
        assertThat(solution(1, 1)).isEqualTo(1);
        assertThat(solution(2, 2)).isEqualTo(2);
        assertThat(solution(3, 2)).isEqualTo(3);
        assertThat(solution(2, 3)).isEqualTo(3);
        assertThat(solution(3, 3)).isEqualTo(6);
        assertThat(solution(4, 3)).isEqualTo(10);
    }

    /**
     * We can define an array R[m][n] which each position contains all the different ways of getting to point (m,n)
     * For example: R[2][3] contains all the different ways you can reach the cell in pos (2,3)
     *
     * Since you can only reach R[m][n] from two different positions:
     * - The cell on the left: R[m-1][n]
     * - The cell on top of it: R[m][n-1]
     *
     * Given that, you can define each element as:
     * R[m][n] = R[m-1][n] + R[m][n-1]
     *
     * Making sure though those positions exists.
     *
     * Base cases:
     * R[0][0] = 1
     *
     */
    private int solution(int m, int n) {
        if(m == 0 || n == 0) return 1; //If there is no two-dimension, there is only one way
        if(m == 1 && n == 1) return 1;

        int[][] R = new int[m][n];

        for(int x = 0 ; x < m ; x++) {
            for(int y = 0 ; y < n ; y++) {
                if(x == 0 && y == 0) {
                    R[0][0] = 1;
                } else {
                    R[x][y] = ((x - 1) >= 0 ? R[x - 1][y] : 0) + ((y - 1) >= 0 ? R[x][y - 1] : 0);
                }
            }
        }

        return R[m-1][n-1];
    }


    @Test
    public void onTheWayHome2() {

        assertThat(solution2(0, 0)).isEqualTo(1);
        assertThat(solution2(1, 1)).isEqualTo(1);
        assertThat(solution2(2, 2)).isEqualTo(2);
        assertThat(solution2(3, 2)).isEqualTo(3);
        assertThat(solution2(2, 3)).isEqualTo(3);
        assertThat(solution2(3, 3)).isEqualTo(6);
        assertThat(solution2(4, 3)).isEqualTo(10);
    }

    /**
     * Same as previous one but simplifying the expressions by adding more base cases.
     *
     * Base cases:
     * R[0][n] = 1 // You can only go right to get to that point
     * R[m][0] = 1 // You can only go down to get to that point
     *
     */
    private int solution2(int m, int n) {
        if(m == 0 || n == 0) return 1; //If there is no two-dimension, there is only one way
        if(m == 1 && n == 1) return 1;

        int[][] R = new int[m][n];

        for(int x = 0 ; x < m ; x++) {
            for(int y = 0 ; y < n ; y++) {
                if(x == 0 || y == 0) {
                    R[x][y] = 1;
                } else {
                    R[x][y] = R[x - 1][y] + R[x][y - 1];
                }
            }
        }

        return R[m-1][n-1];
    }
}
