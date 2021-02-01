package com.jfcorugedo.codility;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A binary gap within a positive integer N is any maximal sequence of consecutive zeros that is surrounded by ones at both ends in the binary representation of N.
 *
 * For example, number 9 has binary representation 1001 and contains a binary gap of length 2. The number 529 has binary representation 1000010001 and contains two binary gaps: one of length 4 and one of length 3. The number 20 has binary representation 10100 and contains one binary gap of length 1. The number 15 has binary representation 1111 and has no binary gaps. The number 32 has binary representation 100000 and has no binary gaps.
 *
 * Write a function:
 *
 * class Solution { public int solution(int N); }
 *
 * that, given a positive integer N, returns the length of its longest binary gap. The function should return 0 if N doesn't contain a binary gap.
 *
 * For example, given N = 1041 the function should return 5, because N has binary representation 10000010001 and so its longest binary gap is of length 5. Given N = 32 the function should return 0, because N has binary representation '100000' and thus no binary gaps.
 */
public class BinaryGap {

    @Test
    public void binaryGap(){

        assertThat(solution(1041)).isEqualTo(5);
        assertThat(solution(15)).isZero();
        assertThat(solution(32)).isZero();
    }

    public int solution(int N) {

        System.out.println(Integer.toBinaryString(N));
        System.out.println(Arrays.toString(Integer.toBinaryString(N).replaceAll("^0+", "").replaceAll("0+$", "").split("1")));
        System.out.println(Arrays
                .stream(Integer.toBinaryString(N).split("1"))
                .filter(gap -> gap.length() > 0).collect(Collectors.toList()));


        return Arrays
                .stream(Integer.toBinaryString(N).split("1"))
                .filter(gap -> gap.length() > 0)
                .map(gap -> gap.split(""))
                .mapToInt(gap -> gap.length)
                .reduce( (acc, current) -> {
                    System.out.println(String.format("%d %d", acc, current));
                    return acc > current ? acc : current;
                }).orElse(0);

    }

    @Test
    public void replaceAll() {
        assertThat("000100000".replaceAll("^0+", "").replaceAll("0+$", "")).isEqualTo("1");
    }
}
