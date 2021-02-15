package com.jfcorugedo.udemy.dynamicprogramming2;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.max;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given two sequence X[1..m] and Y[1..n], find the longest common subsequence between them.
 *
 * It has a lot of application in Genetics.
 *
 * Example:
 *
 * X = ["A", "B", "A", "C", " ", "B", "D", "A", "B" ]
 *
 * Y = [" ", "B", "D", "C", "A", "B", " ", "A" ]
 *
 * Output: "BCBA"
 *
 */
public class LongestCommonSubsequence {

    @Test
    public void longestCommonSubsequence() {


        assertThat(
                solution(
                        asList("A", "B", "A"),
                        asList("A", "B", "A")
                )
        ).isEqualTo(3);//ABA

        assertThat(
                solution(
                        asList("A", "B", "A", "C", "B", "D", "A", "B"),
                        asList("B", "D", "C", "A", "B", "A")
                )
        ).isEqualTo(4);//BDAB

        assertThat(
                solution(
                        asList("A", "5", "B", "D", "B", "B", "B", "A"),
                        asList("B", "D", "C", "B", "B", "A")
                )
        ).isEqualTo(5);//BDBBA
    }

    private int solution(List<String> x, List<String> y) {
        // Let's use two dimensions dynamic programming to build an array R[i][j] where each position contains the maximum
        // subsequence of 'x' with length i and 'y' with length j.
        // So R[4][3] contains the maximum subsequence of 'x' with length 4 and 'y' with length 3

        int[][] R = new int[x.size() + 1][y.size() + 1];

        for(int i = 1 ; i <= x.size() ; i++) {
            for(int j = 1 ; j <= y.size() ; j++) {
                if(x.get(i-1).equals(y.get(j-1))) {
                    R[i][j] = R[i-1][j-1] + 1;
                } else {
                    R[i][j] = max(R[i-1][j], R[i][j-1]);
                }
            }
        }

        System.out.println(Arrays.deepToString(R));
        return R[x.size()][y.size()];
    }


}
