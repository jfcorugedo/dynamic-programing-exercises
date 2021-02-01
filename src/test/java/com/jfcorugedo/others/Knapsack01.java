package com.jfcorugedo.others;

import com.jfcorugedo.others.dto.Item;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given a set of n items, where item i has weight w[i] and value v[i], and a knapsack with capacity W.
 *
 * Create an algorithm that maximize the value of the elements inside the knapsack without exceeding the maximum weight.
 *
 * Example:
 * W = 8
 * n = 3
 * w[1] = 2, v[1] = 10
 * w[2] = 5, v[2] = 12
 * w[3] = 8, v[3] = 21
 *
 * Output: object 1 and object 2, so w = 2 + 5 = 7 < 8 and v = 10 + 12 = 22
 *
 *
 */
public class Knapsack01 {

    /**
     * We are going to use two dimensions:
     *
     * R[i][j] will be the maximum value of knapsack with capacity j using the first i elements only.
     *
     * Two cases:
     * - Use the element i
     * - Don't use the element i
     *
     * If we use the element, then:
     * R[i][j] = v[i] + R[i-1][j-w[i]]
     *
     * If we don't use the i element then:
     * R[i][j] = R[i-1][j]
     *
     * So:
     *  R[i][j] = max{v[i] + R[i-1][j-w[i]], R[i-1][j]}
     *
     *  R[i][j] = 0 if i = 0 or j = 0
     *
     *  The solution will be: R[n][W] where n is the number of elements and W the capacity or our knapsack.
     *
     */
    @Test
    public void knapsack01() {

        assertThat(
                solution(
                        Arrays.asList(
                                Item.builder().value(10).weight(2).build()
                        ),
                        8)
        ).isEqualTo(10);
        assertThat(
                solution(
                        Arrays.asList(
                                Item.builder().value(10).weight(2).build(),
                                Item.builder().value(12).weight(5).build(),
                                Item.builder().value(20).weight(8).build()
                        ),
                        8)
        ).isEqualTo(22);
        assertThat(
                solution(
                        Arrays.asList(
                                Item.builder().value(10).weight(5).build(),
                                Item.builder().value(40).weight(4).build(),
                                Item.builder().value(30).weight(6).build(),
                                Item.builder().value(50).weight(3).build()
                        ),
                        9)
        ).isEqualTo(90);
    }

    public int solution(List<Item> items, int knapsackMaxWeight) {

        items.sort(Comparator.comparingInt(a -> a.getWeight()));

        if(items.size() == 1) return items.get(0).getValue();

        int[][] R = new int[items.size()+1][knapsackMaxWeight+1];

        for(int i = 1 ; i <= items.size() ; i++) {
            for(int j = 1 ; j <= knapsackMaxWeight ; j++) {
                int weightLeft = j - items.get(i-1).getWeight();
                if(weightLeft >= 0 && items.get(i-1).getValue() + R[i-1][weightLeft] > R[i-1][j]) {
                    R[i][j] = items.get(i-1).getValue() + R[i-1][weightLeft];
                } else {
                    R[i][j] = R[i-1][j];
                }
            }
        }

        return R[items.size()][knapsackMaxWeight];
    }
}
