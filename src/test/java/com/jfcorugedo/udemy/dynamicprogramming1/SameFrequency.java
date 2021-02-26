package com.jfcorugedo.udemy.dynamicprogramming1;

import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Write a function that given two positive integers, find out if the two numbers have the same frequency of digits.
 *
 * Example:
 *
 * sameFrequency(182, 281) => true
 * sameFrequency(34, 14) => false
 * sameFrequency(3589578, 5879385) => true
 * sameFrequency(22, 222) => false
 */
public class SameFrequency {

    @Test
    public void sameFrequency() {
        assertThat(solution(0, 0)).isEqualTo(true);
        assertThat(solution(22, 222)).isEqualTo(false);
        assertThat(solution(182, 281)).isEqualTo(true);
        assertThat(solution(34, 14)).isEqualTo(false);
        assertThat(solution(3589578, 5879385)).isEqualTo(true);
    }

    private boolean solution(Integer a, Integer b) {

        if(a.equals(b)) return true;
        if(a.toString().length() != b.toString().length()) return false;

        Map<Character, Integer> aFrequency = computeFrequency(a);
        Map<Character, Integer> bFrequency = computeFrequency(b);

        boolean same = aFrequency.entrySet().equals(bFrequency.entrySet());


        return same;
    }

    private Map<Character, Integer> computeFrequency(Integer a) {
        Map<Character, Integer> aFrequency = new TreeMap<>();
        for(char number : a.toString().toCharArray()) {
            aFrequency.compute(number, (key, value) -> (value == null) ? 1 : value + 1);
        }
        return aFrequency;
    }
}
