package com.jfcorugedo.others;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Say you have an array for which each position contains the price of a given stock on that day.
 * So P[3] is price of the stock day 3.
 *
 * If you were permitted to complete at most one transaction (a buy and a sell or nothing), design an algorithm to
 * find the maximum profit.
 *
 * Example:
 *
 * Input = [7, 1, 5, 3, 6, 4]
 * Output = 5 -> Buy on day 2 at price 1 and sell on day 5 at price 6 (6-1=5)
 *
 * Another interesting example:
 *
 * Input = [7, 6, 5, 4, 3, 1]
 * Output = 0 (because it is descending, there is no way of buying one day and selling more expensive next days)
 *
 *
 */
public class BestTimeToBuyAndSellStock {

    @Test
    public void bestTimeToBuyAndSell() {

        assertThat(solution(Arrays.asList(1, 5))).isEqualTo(4);
        assertThat(solution(Arrays.asList(1, 5, 3, 6))).isEqualTo(5);
        assertThat(solution(Arrays.asList(7, 1, 5, 3, 6, 4))).isEqualTo(5);
        assertThat(solution(Arrays.asList(7, 1, 5, 3, 6, 7))).isEqualTo(6);
        assertThat(solution(Arrays.asList(4,3,2,1))).isEqualTo(0);
    }

    /**
     * Given R[n] as the maximum price of buying and selling from day 1 to day n.
     *
     * Given that, at day n, there are two possibilities:
     * - You sell at day n
     * - You don't sell at day n
     *
     * If you sell at day n, the the maximum profit comes from buying the previous days at the minimum price:
     * R[n] = prices(n) - min{prices(n-1), prices(n-2), ..., prices(1)}
     *
     * If you don't sell at day n, then the maximum profit is R[n-1].
     *
     * So the expression should be:
     * R[n] = max{R[n-1}, prices(n) - min{prices(n-1), prices(n-2), ..., prices(1)} }
     *
     * Base case:
     * R[0] = 0
     * R[1] = 0 //if there is only one day, there is no way to buy and sell
     *
     * @param prices
     * @return
     */
    private int solution(List<Integer> prices) {

        int[] R = new int[prices.size()+1];
        R[0] = 0;
        R[1] = 0;
        int minValue = prices.get(0);

        for(int i = 2; i <= prices.size() ; i++) {
            minValue = min(minValue, prices.get(i-1));
            R[i] = max(
                    R[i-1],
                    prices.get(i-1) - minValue
            );
        }

        return R[prices.size()];
    }

    /**
     * Same problem but now you can perform more than one trasaction. You an buy and sell many times, but you have
     * to sell before buying again
     */
    @Test
    public void bestTimeToBuyAndSell2() {

        assertThat(solution2(Arrays.asList(1, 5))).isEqualTo(4);
        assertThat(solution2(Arrays.asList(1, 5, 3, 6))).isEqualTo(7);
        assertThat(solution2(Arrays.asList(7, 1, 5, 3, 6, 4))).isEqualTo(7);
        assertThat(solution2(Arrays.asList(7, 1, 5, 3, 6, 7))).isEqualTo(8);
        assertThat(solution(Arrays.asList(4,3,2,1))).isEqualTo(0);
        assertThat(solution(IntStream.range(1, 1001).boxed().collect(Collectors.toList()))).isEqualTo(999);
    }

    /**
     * This time you can perform more than one transaction. In fact you can perform as many as you like.
     *
     * The only constrain is that you need to sell before buying again.
     *
     * R[n] = max{prices(n) - price(n-1) + R[n-2], prices(n) - price(n-2) + R[n-3], ..., price(n) - price(1) + R[0]}
     *
     * @param prices
     * @return
     */
    private int solution2(List<Integer> prices) {

        if(prices.size() == 2) return max(0, prices.get(1) - prices.get(0));
        int[] R = new int[prices.size()+1];
        R[0] = 0;
        R[1] = 0;

        int maxValue = -1;
        for(int i = 2; i <= prices.size() ; i++) {
            for(int j = i-1 ; j >= 0 ; j--) {
                maxValue = max(maxValue, prices.get(i-1) - prices.get(i-j-1) + R[i-j-1]);
            }
            R[i] = maxValue;
        }

        return R[prices.size()];
    }
}
