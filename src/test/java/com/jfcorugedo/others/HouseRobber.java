package com.jfcorugedo.others;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.max;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that
 * adjacent houses have security system connected and it will automatically contact if two adjacent houses were broken
 * into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount
 * of money you can rob tonight without alerting the police.
 *
 */
public class HouseRobber {

    @Test
    public void houseRobber() {

        assertThat(solution(1, Arrays.asList(1))).isEqualTo(1);
        assertThat(solution(2, Arrays.asList(1, 4))).isEqualTo(4);
        assertThat(solution(2, Arrays.asList(4, 1))).isEqualTo(4);
        assertThat(solution(3, Arrays.asList(4, 1, 5))).isEqualTo(9);
        assertThat(solution(1, Arrays.asList(4, 10, 5))).isEqualTo(10);
        assertThat(solution(4, Arrays.asList(4, 10, 5, 1))).isEqualTo(11);
        assertThat(solution(4, Arrays.asList(4, 10, 8, 1))).isEqualTo(12);
        assertThat(solution(5, Arrays.asList(1, 2, 3, 4, 5))).isEqualTo(9);
        assertThat(solution(5, Arrays.asList(1, 9, 3, 4, 5))).isEqualTo(14);
        assertThat(solution(7, Arrays.asList(10, 9, 3, 4, 5, 6, 3))).isEqualTo(21);
        assertThat(solution(1000, IntStream.range(1, 1001).boxed().collect(Collectors.toList()))).isEqualTo(250500);
    }

    /**
     * R[n] = Maximum amount of money you can steal from house 1 to house n
     *
     * Two possibilities:
     * - You steal hose n
     * - You don't steal house n
     *
     * If you decided to steal house n, then you cannot steal house n-1 (due to the alerting system). So the expression
     * that gives us the result is this:
     * Given that you steal house n, then
     * R[n] = money(n) + max{R[n-2], R[n-3], R[n-4], ..., R[1]}
     *
     * If you don't steal house n, then the expression is pretty simple, because you can steal house n-1.
     * Given that you don't steal house n, then:
     * R[n] = R[n-1]
     *
     * So if we combine both we get the final expression:
     *
     * R[n] = max{ R[n-1], money(n) + max(R[n-2], R[n-3], R[n-4], ..., R[1]} }
     *
     * We can simplify the expression:
     *
     * R[n] = max{ R[n-1], money(n) + R[n-2], money(n) + R[n-3], money(n) + R[n-4], ..., money(n) + R[1] }
     *
     * Base case:
     * R[0] = 0 //if there is no hoses, you cannot steal any money
     * R[1] = money(1) //if there is only one hose, there is no option. The best choice is to rob that house
     *
     * Examples:
     * R[0] = 0
     * R[1] = money(1)
     * R[2] = max(R[1], money(2) + R[0] }
     * R[3] = max{ R[2], money(3) + R[1], money(3) + R[0] }
     * R[4] = max{ R[3], money(4) + R[2], money(4) + R[1], money(4) + R[0] }
     * ....
     *
     * But we can improve this solution even more. There is no point in calculating R[n-3], R[n-4]...
     * Because R[n-2] is by definition the maximum amount you can get from houses 1 to n-2, so the expression can be this one:
     *
     * R[n] = max{R[n-1], money(n) + R[n-2]}
     */
    private int solution(int houses, List<Integer> money) {

        if( houses <= 2) {
            return money.stream().max(Comparator.comparingInt(a -> a)).orElse(-1);
        }
        int[] R = new int[houses + 1];
        R[0] = 0;
        R[1] = money.get(0);

        for(int i = 2 ; i <= houses ; i++) {

            R[i] = max(R[i-1], money.get(i-1) + R[i-2]);
            /* Old way
            int maxValue = R[i-1];
            for (int j = i - 2; j >= 0; j--) {
                maxValue = max(maxValue, money.get(i - 1) + R[j]);
            }
            R[i] = maxValue;
             */


        }

        return R[houses];
    }
}
