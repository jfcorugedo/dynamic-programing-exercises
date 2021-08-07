package com.jfcorugedo.udemy.javascriptalgorithmsanddatastructures;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * We want to write a function that computes the sum of all the numbers from 1 to n.
 *
 * Some examples:
 *
 * Given 3, then it will return 6 (1 + 2+ 3)
 * Given 100, then it will return 5050
 */
public class SumUpTo {

    @Test
    public void sumUpToOneReturnsOne() {

        assertThat(solution(1)).isEqualTo(1);
    }

    @Test
    public void sumUpToLessThanOneReturnsZero() {

        assertThat(solution(-10)).isZero();
    }

    @Test
    public void sumUpToThreeReturnsSix() {

        assertThat(solution(3)).isEqualTo(6);
    }

    @Test
    public void sumUpToOneHundredReturnsFiveThousandsAndFifty() {

        assertThat(solution(100)).isEqualTo(5050);
    }

    /**
     * Simple solution using an acumulator and a for loop
     *
     */
    private int solution(int n) {
        if(n < 1) return 0;
        int total = 0;
        for(int i = 1 ; i <= n ; i++) total += i;
        return total;
    }

    // ----------- Zomg Wut Approach ----------------

    @Test
    public void sumUpToOneReturnsOneWithZomgWutApproach() {

        assertThat(solutionZomgWut(1)).isEqualTo(1);
    }

    @Test
    public void sumUpToLessThanOneReturnsZeroWithZomgWutApproach() {

        assertThat(solutionZomgWut(-10)).isZero();
    }

    @Test
    public void sumUpToThreeReturnsSixWithZomgWutApproach() {

        assertThat(solutionZomgWut(3)).isEqualTo(6);
    }

    @Test
    public void sumUpToOneHundredReturnsFiveThousandsAndFiftyWithZomgWutApproach() {

        assertThat(solutionZomgWut(100)).isEqualTo(5050);
    }

    /**
     * Elegant solution using Zomg Wut description of the problem:
     *
     *   sumUpTo(n) = 1 + 2 + 3 + ... + n-1 + n
     * + sumUpTo(n) = n + n-1 + ... + 3 + 2 + 1
     * -----------------------------------------
     * 2*sumUpTo(n) = (n + 1) + (n + 1) + (n + 1) + ... + (n + 1)
     *
     * So we can infer that:
     *
     * sumUpTo(n) = n*(n + 1)/2
     *
     */
    private int solutionZomgWut(int n) {
        if(n < 1) return 0;
        return n * (n + 1) /2;
    }
}
