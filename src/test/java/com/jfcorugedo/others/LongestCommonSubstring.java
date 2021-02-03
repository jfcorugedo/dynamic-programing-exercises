package com.jfcorugedo.others;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given two string X[1..m] and Y[1..n], we want to find the longest common substring between the two.
 *
 * Example:
 *
 * X = DEADBEEF
 * Y = EATBEEF
 *
 * Output: 4. Two substrings "EA" and "BEEF", the longest has length 4.
 *
 */
public class LongestCommonSubstring {

    @Test
    public void longestCommonSubstring() {

        assertThat(solution("A", "B")).isEqualTo(0);
        assertThat(solution("A", "A")).isEqualTo(1);
        assertThat(solution("ABC", "ABC")).isEqualTo(3);
        assertThat(solution("DEADBEEF", "EATBEEF")).isEqualTo(4);
    }

    private int solution(String x, String y) {

        List<String> X = Arrays.stream(x.split("")).collect(Collectors.toList());
        List<String> Y = Arrays.stream(y.split("")).collect(Collectors.toList());

        /*
        Let's use a two dimension dynamic programing approach. Each position contains the maximum substring using
        'x' length i and 'y' length j.
        The solution will be the maximum of all those values
         */
        int R[][] = new int[X.size() + 1][Y.size() + 1];

        int maxValue = 0;
        for(int i = 1 ; i <= X.size() ; i++) {
            for(int j = 1 ; j <= Y.size() ; j++) {
                if(X.get(i-1).equals(Y.get(j-1))) {
                    R[i][j] = R[i-1][j-1] +1;
                    if(R[i][j] > maxValue) maxValue = R[i][j];
                } //There is no else, because if they don't match, the result is 0 (there cannot be elements in between)
            }
        }

        System.out.println(Arrays.deepToString(R));

        return maxValue;
    }
}
