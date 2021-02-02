package com.jfcorugedo.others;

import com.jfcorugedo.others.dto.Item;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.max;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * Given a set of n items, where item i has weight w[i] and value v[i], and a knapsack with capacity W.
 *
 * Create an algorithm that maximize the value of the elements inside the knapsack without exceeding the maximum weight.
 *
 * You can take as many objects as you want form each type.
 *
 * Example:
 * W = 8
 * n = 3
 * w[1] = 2, v[1] = 10
 * w[2] = 5, v[2] = 12
 * w[3] = 8, v[3] = 21
 *
 * Output: 40 (4 objects of type 1)
 *
 * Input : W = 8
 *        val[] = {10, 40, 50, 70}
 *        wt[]  = {1, 3, 4, 5}
 * Output : 110
 *
 */
public class UnboundedKnapsack {

    /**
     * Now we only need a one-dimension array. R[i] will contains the maximum value of a knapsack of weight i.
     *
     * Base case:
     * R[0] = 0
     */
    @Test
    public void knapsack01() {

        assertThat(solution(asList(Item.builder().value(10).weight(1).build()),8)).isEqualTo(80);
        assertThat(
                solution(
                        asList(
                                Item.builder().value(10).weight(1).build(),
                                Item.builder().value(40).weight(7).build()
                        ),
                        8)
        ).isEqualTo(80);
        assertThat(
                solution(
                        asList(
                                Item.builder().value(10).weight(1).build(),
                                Item.builder().value(40).weight(3).build()
                        ),
                        8)
        ).isEqualTo(100);
        assertThat(
                solution(
                        asList(
                                Item.builder().value(10).weight(1).build(),
                                Item.builder().value(40).weight(3).build(),
                                Item.builder().value(50).weight(4).build(),
                                Item.builder().value(70).weight(5).build()
                        ),
                        8)
        ).isEqualTo(110);
    }

    public int solution(List<Item> items, int knapsackMaxWeight) {

        int[] R = new int[knapsackMaxWeight + 1];
        R[0] = 0;

        //If we choose to put the element: value(n) + R[n - weight(n)
        //if not: R[n-1]
        //R[n] = max{R[n-1], value(n) + R[n - weight(n)] }
        //making sure though we cannot exceed the maximum weight of the knapsack
        for(int i = 1 ; i <= knapsackMaxWeight ; i++) {
            for(int j = 0 ; j < items.size() ; j++) {
                if(items.get(j).getWeight() <= i) {
                    R[i] = max(R[i], items.get(j).getValue() + R[i - items.get(j).getWeight()]);
                }
            }
        }

        return R[knapsackMaxWeight];
    }
}


