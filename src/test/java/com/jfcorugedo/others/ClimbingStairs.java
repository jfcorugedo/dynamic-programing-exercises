package com.jfcorugedo.others;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You can climb either 1 or 2 stairs at a time, how many different ways you can climb N stairs
 */
public class ClimbingStairs {


    @Test
    public void climbingStairs1() {

        assertThat(solution(1)).isEqualTo(1);
        assertThat(solution(2)).isEqualTo(2);
        assertThat(solution(3)).isEqualTo(3);
        assertThat(solution(4)).isEqualTo(5);
        assertThat(solution(10)).isEqualTo(89);
        assertThat(solution(45)).isEqualTo(1836311903);
    }

    /**
     * f(n) = f(n-1) + f(n-2)
     * and when n is 1, there is only one way of getting to the top.
     * When n is 2 there are two ways of getting to the top.
     * This solution has a complexity of O(2^n) (exponential) so it is not a really good choice for large numbers
     * @param steps
     * @return
     */
    private int solution(int steps) {
        if(steps == 1) return 1;
        if(steps == 2) return 2;

        return solution(steps-1) + solution(steps-2);
    }

    @Test
    public void climbingStairs2() {

        assertThat(solution2(1)).isEqualTo(1);
        assertThat(solution2(2)).isEqualTo(2);
        assertThat(solution2(3)).isEqualTo(3);
        assertThat(solution2(4)).isEqualTo(5);
        assertThat(solution2(10)).isEqualTo(89);
        assertThat(solution2(45)).isEqualTo(1836311903);
    }

    /**
     * S[n] where each element contains the solution for n steps.
     * Given that S[1] = 1 and S[2] = 2.
     * And given that S[i] = S[i-1] + S[i-2]
     * This solution has a O(n) complexity.
     * @param steps
     * @return
     */
    private int solution2(int steps) {

        if(steps == 1) return 1;
        if(steps == 2) return 2;

        int[] S = new int[steps+1];
        S[1] = 1;
        S[2] = 2;

        for(int i = 3 ; i <= steps ; i++) {
            S[i] = S[i-1] + S[i-2];
        }

        return S[steps];
    }

    /**
     * Now using streams instead of arrays
     */
    @Test
    public void climbingStairs3() {

        assertThat(solution2(1)).isEqualTo(1);
        assertThat(solution2(2)).isEqualTo(2);
        assertThat(solution2(3)).isEqualTo(3);
        assertThat(solution2(4)).isEqualTo(5);
        assertThat(solution2(10)).isEqualTo(89);
        assertThat(solution2(45)).isEqualTo(1836311903);
    }

    /**
     * S[n] where each element contains the solution for n steps.
     * Given that S[1] = 1 and S[2] = 2.
     * And given that S[i] = S[i-1] + S[i-2]
     * This solution has a O(n) complexity.
     * @param steps
     * @return
     */
    private int solution3(int steps) {

        //S(0) = 0
        //S(1) = 1
        //S(2) = 2
        if(steps <= 2) return steps;

        //S(n) = S(n-1) + S(n-2)
        List<Integer> result = IntStream
                .range(0, steps + 1)
                .mapToObj(Integer::valueOf)
                .reduce(new ArrayList<Integer>(), (acc, index) -> {
                    if(index <= 2) {
                        acc.add(index);
                    } else {
                        acc.add(acc.get(index - 1) + acc.get(index - 2));
                    }
                    return acc;
                }, (acc1, acc2) -> {acc1.addAll(acc2); return acc1;});


        return result.get(steps);
    }

    /**
     * Now climbing 1, 2 or three steps at a time
     */
    @Test
    public void climbingStairs4() {

        assertThat(solution4(1)).isEqualTo(1);
        assertThat(solution4(2)).isEqualTo(2);
        assertThat(solution4(3)).isEqualTo(4);
        assertThat(solution4(4)).isEqualTo(7);
        assertThat(solution4(10)).isEqualTo(274);
        assertThat(solution4(45)).isEqualTo(2082876103);
    }

    /**
     * S[n] where each element contains the solution for n stairs.
     * Given that S[1] = 1 and S[2] = 2.
     * And given that S[i] = S[i-1] + S[i-2]
     * This solution has a O(n) complexity.
     * @param steps
     * @return
     */
    private int solution4(int steps) {

        if(steps == 1) return 1;
        if(steps == 2) return 2;
        if(steps == 3) return 4;

        int[] S = new int[steps+1];
        S[1] = 1;
        S[2] = 2;
        S[3] = 4;
        int res = IntStream.range(4, steps+1).map(
                index -> {
                    S[index] = S[index-1] + S[index-2] + S[index - 3];
                    return S[index];
                }
        ).max().orElse(-1);

        System.out.println("===> " + res);
        return res;
    }


    @Test
    public void climbingStairs5() {

        assertThat(solution5(1, 2)).isEqualTo(1);
        assertThat(solution5(2, 2)).isEqualTo(2);
        assertThat(solution5(3, 2)).isEqualTo(3);
        assertThat(solution5(4, 2)).isEqualTo(5);
        assertThat(solution5(10, 2)).isEqualTo(89);
        assertThat(solution5(45, 2)).isEqualTo(1836311903);
    }

    /**
     * Count number of ways
     * to reach n't stair when a person
     * can climb 1, 2, ..m stairs at a time
     * @param steps
     * @param ways
     * @return
     */
    private int solution5(int steps, int ways) {

        if(steps == 1) return 1;
        if(steps == 2) return 2;

        int[] S = new int[steps+1];
        S[1] = 1;
        S[2] = 2;

        for(int i = 3 ; i <= steps ; i++) {
            S[i] = 0;
            for(int j = 1 ; j <= ways && j <= i ; j++) {
                S[i] += S[i-j];
            }
        }
        return S[S.length-1];
    }
}
