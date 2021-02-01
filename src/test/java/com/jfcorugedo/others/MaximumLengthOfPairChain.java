package com.jfcorugedo.others;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.max;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are given n pair of numbers. In each pair, the first number is always smaller than the second one.
 *
 * Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in
 * this fashion.
 *
 * Given a set of pairs, find the length of the longest chain you can build following the previous constraint.
 * You can select pairs in any order and it is no mandatory to use all of them.
 *
 * Example:
 * Input: [ [1,2], [2,3], [3,4] ]
 * Output: 2 -> [1,2]-[3,4] (because 3 > 2)
 *
 *
 */
public class MaximumLengthOfPairChain {

    @Test
    public void maximumLengthOfPairChain() {

        assertThat(
                solution(Arrays.asList(Arrays.asList(1,2)))
        ).isEqualTo(1);
        assertThat(
                solution(Arrays.asList(Arrays.asList(1,2), Arrays.asList(2,3), Arrays.asList(3,4)))
        ).isEqualTo(2);
        assertThat(
                solution(
                        Arrays.asList(
                                Arrays.asList(1,2),
                                Arrays.asList(2,5),
                                Arrays.asList(2,3),
                                Arrays.asList(3,4),
                                Arrays.asList(3,5)
                        )
                )
        ).isEqualTo(2);
        assertThat(
                solution(
                        Arrays.asList(
                                Arrays.asList(1,2),
                                Arrays.asList(3,5),
                                Arrays.asList(6,8),
                                Arrays.asList(3,4),
                                Arrays.asList(9,10)
                        )
                )
        ).isEqualTo(4);
        assertThat(
                solution(
                        Arrays.asList(
                                Arrays.asList(0,1),
                                Arrays.asList(2,3),
                                Arrays.asList(6,8),
                                Arrays.asList(4,5),
                                Arrays.asList(9,10)
                        )
                )
        ).isEqualTo(5);
    }

    public int solution(List<List<Integer>> pairs) {

        //Sort by first element and if both are the same sort by the second element
        pairs.sort((a, b) -> {
            int diff = a.get(0) - b.get(0);
            return diff != 0 ? diff : a.get(1) - b.get(1);
        });
        // R[n] = 1+ max{R[n-1], R[n-2], ....}
        int R[] = new int[pairs.size()+1];
        R[0] = 0;
        R[1] = 1;

        for(int i = 2 ; i <= pairs.size() ; i++) {
            R[i] = R[i-1]; //In case R[i] cannot be chosen, this will be the maximum
            for(int j = i-1 ; j > 0 ; j--) {
                if(pairs.get(i-1).get(0) > pairs.get(j-1).get(1)) {
                    R[i] = max(R[j] + 1, R[i]);
                }
            }
        }

        return R[pairs.size()];
    }

    @Test
    public void maximumLengthOfPairChain2() {

        assertThat(
                solution2(Arrays.asList(Arrays.asList(1,2)))
        ).isEqualTo(1);
        assertThat(
                solution2(Arrays.asList(Arrays.asList(1,2), Arrays.asList(2,3), Arrays.asList(3,4)))
        ).isEqualTo(2);
        assertThat(
                solution2(
                        Arrays.asList(
                                Arrays.asList(1,2),
                                Arrays.asList(2,5),
                                Arrays.asList(2,3),
                                Arrays.asList(3,4),
                                Arrays.asList(3,5)
                        )
                )
        ).isEqualTo(2);
        assertThat(
                solution2(
                        Arrays.asList(
                                Arrays.asList(1,2),
                                Arrays.asList(3,5),
                                Arrays.asList(6,8),
                                Arrays.asList(3,4),
                                Arrays.asList(9,10)
                        )
                )
        ).isEqualTo(4);
        assertThat(
                solution2(
                        Arrays.asList(
                                Arrays.asList(0,1),
                                Arrays.asList(2,3),
                                Arrays.asList(6,8),
                                Arrays.asList(4,5),
                                Arrays.asList(9,10)
                        )
                )
        ).isEqualTo(5);
    }

    public int solution2(List<List<Integer>> pairs) {

        //Sort by first element and if both are the same sort by the second element
        pairs.sort((a, b) -> {
            int diff = a.get(0) - b.get(0);
            return diff != 0 ? diff : a.get(1) - b.get(1);
        });

        int maxChain = 1;

        for(int i = pairs.size()-1, j = pairs.size()-2 ; j >= 0 ; j--) {
            if(pairs.get(i).get(0) > pairs.get(j).get(1)) {
                maxChain++;
                i = j;
            }
        }

        return maxChain;
    }
}
