package com.jfcorugedo.others;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given a rod of length n and prices P[i] for i=1, 2, ..., n where P[i] is the
 * price of a rod of length i. Find the maximum total revenue you can make by
 * cutting and selling the rod (Assume no cost for cutting the rod)
 */
public class CuttingRods {

    /**
     * A solution could be to sell a first piece of the rod and calculate the maximum of the rest:
     * p1 + r(n-1) (Cut a length-1 piece and calculate the rest)
     * p2 + r(n-2) (Cut a length-2 piece and calculate the rest)
     * ...
     * p(n-1) + r1
     * p(n) (Sell the whole rod without cutting anything)
     *
     * So the solution will be the maximum value of this series:
     * max(p1 + r(n-1), p2 + r(n-2), ...., p(n-1) + r1, p(n))
     *
     * This solution has exponential complexity O(n^2)
     */
    @Test
    public void cuttingRods() {

        assertThat(revenue(5, Arrays.asList(1,5,8,9,10))).isEqualTo(13);
        assertThat(revenue(10, Arrays.asList(1,5,8,9,10,17,17,20,24,30))).isEqualTo(30);
        assertThat(revenue(30, IntStream.range(1,31).mapToObj(i -> i).collect(Collectors.toList()))).isEqualTo(30);
    }

    private int revenue(int length, List<Integer> prices) {
        if(length == 0) return 0; //A rod with no length does not have any value

        int maxValue = -1;
        for(int i = 1 ; i <= length ; i++) {
            int value = prices.get(i-1) + revenue(length-i, prices);
            if(value > maxValue) maxValue = value;
        }

        return maxValue;
    }


    /**
     * Improve implementation now it has O(n) complexity
     *
     * R[n] = max revenue of a rod with size n
     * R[n] = max{P[1] + R[n-1], P[2] + R[n-2], ...., P[n]}
     *
     * i.e: R[4] = max{P[1] + R[3], P[2] + R[2], P[3] + R[1], P[4]}
     *
     */
    @Test
    public void cuttingRods2() {

        assertThat(revenue2(5, Arrays.asList(1,5,8,9,10))).isEqualTo(13);
        assertThat(revenue2(10, Arrays.asList(1,5,8,9,10,17,17,20,24,30))).isEqualTo(30);
        assertThat(revenue2(30, IntStream.range(1,31).mapToObj(i -> i).collect(Collectors.toList()))).isEqualTo(30);
    }

    private int revenue2(int length, List<Integer> prices) {
        List<Integer> R = new ArrayList<>(length+1);
        R.add(0, 0);

        for(int i = 1 ; i <=length ; i++) {
            int maxValue = -1;
            for(int j = 1 ; j <= i ; j++) {
                int temp = prices.get(j-1) + R.get(i-j);
                if(maxValue < temp) maxValue = temp;
            }
            R.add(i, maxValue);
        }



        return R.stream().mapToInt(i -> i).max().getAsInt();
    }

    private int givenSolution(int n, List<Integer> prices) {
        if(n == 0 ) return 0;
        int maxValue = -1;
        for(int i = 0 ; i < n ; i++) {
            int temp = prices.get(n - i -1) + givenSolution(i, prices);
            if(temp > maxValue) maxValue = temp;
        }
        return maxValue;
    }
}
