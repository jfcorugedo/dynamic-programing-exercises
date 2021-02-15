package com.jfcorugedo.adventofcode2020;

import lombok.SneakyThrows;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This is part of the challenges of Advent of Code 2020 edition.
 *
 *
 *
 * To see all the details about this challenge go to:
 * https://adventofcode.com/2020/day/10
 *
 */
public class Day10AdapterArray {


    /**
     * To completely determine whether you have enough adapters, you'll need to figure out how many different ways they can be arranged. Every arrangement needs to connect the charging outlet to your device. The previous rules about when adapters can successfully connect still apply.
     *
     * The first example above (the one that starts with 16, 10, 15) supports the following arrangements:
     *
     * (0), 1, 4, 5, 6, 7, 10, 11, 12, 15, 16, 19, (22)
     * (0), 1, 4, 5, 6, 7, 10, 12, 15, 16, 19, (22)
     * (0), 1, 4, 5, 7, 10, 11, 12, 15, 16, 19, (22)
     * (0), 1, 4, 5, 7, 10, 12, 15, 16, 19, (22)
     * (0), 1, 4, 6, 7, 10, 11, 12, 15, 16, 19, (22)
     * (0), 1, 4, 6, 7, 10, 12, 15, 16, 19, (22)
     * (0), 1, 4, 7, 10, 11, 12, 15, 16, 19, (22)
     * (0), 1, 4, 7, 10, 12, 15, 16, 19, (22)
     * (The charging outlet and your device's built-in adapter are shown in parentheses.) Given the adapters from the first example, the total number of arrangements that connect the charging outlet to your device is 8.
     *
     * The second example above (the one that starts with 28, 33, 18) has many arrangements. Here are a few:
     *
     * (0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
     * 32, 33, 34, 35, 38, 39, 42, 45, 46, 47, 48, 49, (52)
     *
     * (0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
     * 32, 33, 34, 35, 38, 39, 42, 45, 46, 47, 49, (52)
     *
     * (0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
     * 32, 33, 34, 35, 38, 39, 42, 45, 46, 48, 49, (52)
     *
     * (0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
     * 32, 33, 34, 35, 38, 39, 42, 45, 46, 49, (52)
     *
     * (0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
     * 32, 33, 34, 35, 38, 39, 42, 45, 47, 48, 49, (52)
     *
     * (0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
     * 46, 48, 49, (52)
     *
     * (0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
     * 46, 49, (52)
     *
     * (0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
     * 47, 48, 49, (52)
     *
     * (0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
     * 47, 49, (52)
     *
     * (0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
     * 48, 49, (52)
     * In total, this set of adapters can connect the charging outlet to your device in 19208 distinct arrangements.
     *
     * You glance back down at your bag and try to remember why you brought so many adapters; there must be more than a trillion valid ways to arrange them! Surely, there must be an efficient way to count the arrangements.
     *
     * What is the total number of distinct ways you can arrange the adapters to connect the charging outlet to your device?
     */
    @Test
    public void partTwoNoAdapters() {
        List<Integer> adapters = new ArrayList<>();

        assertThat(solution(adapters)).isEqualTo(1L);
    }

    @Test
    public void partTwoJustOneAdapter() {
        List<Integer> adapters = Arrays.asList(3).stream().collect(Collectors.toList());

        //If there is only one adapter, the only way of adapting is using it
        assertThat(solution(adapters)).isEqualTo(1L);
    }

    @Test
    public void partTwoOnlyTwoAdapters() {
        List<Integer> adapters = Arrays.asList(1, 2).stream().collect(Collectors.toList());

        assertThat(solution(adapters)).isEqualTo(2L);
    }

    @Test
    public void threeAdaptersWithJustOneCombination() {
        List<Integer> adapters = Arrays.asList(1, 4, 7).stream().collect(Collectors.toList());

        assertThat(solution(adapters)).isEqualTo(1L);
    }

    @Test
    public void partTwoSample1() {
        List<Integer> adapters = Arrays.asList(1, 4, 5, 6, 7, 10, 11, 12, 15, 16, 19).stream().collect(Collectors.toList());

        assertThat(solution(adapters)).isEqualTo(8L);
    }

    @Test
    public void partTwoSample2() {
        List<Integer> adapters = Arrays.asList(28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45, 19, 38, 39, 11, 1, 32, 25, 35, 8, 17, 7, 9, 4, 2, 34, 10, 3).stream().collect(Collectors.toList());

        assertThat(solution(adapters)).isEqualTo(19208L);
    }

    @Test
    public void partTwo() {
        List<Integer> adapters = loadAdapters();

        assertThat(solution(adapters)).isEqualTo(338510590509056L);
    }

    private long solution(List<Integer> adapters) {

        //If there is no adapters, there is just one way of connecting your device
        if(adapters.size() == 0) return 1;

        adapters.add(0);
        adapters.sort(Comparator.comparingInt(n -> n));

        /*
        Given an array R where R[i] contains how many possibilities there are to get to 'i' jolts.
        Given any value 'i' representing a jolt voltage, there are two possibilities:
        - There is an adapter with that output
        - There isn't any adapter with that output

        If there isn't any adapter, then the possibilites to get to that jolts are 0.
        If there is an adapter, the the possibilities are the sum of getting to up to 3 lower jotls.
        R[i] = R[i-1] + R[i-2] + R[i-3]
        Since each adapter can accept up to 3 jolts lower that itself.

        Base case:
        R[0] = 1; To get to 0 jolts there is only one possibility: With no adapters
        R[1] = R[0] or 0 if there is no adapter with that output
        R[2] = R[0] + R[1] or 0 if there is no adapter with that output
        */
        int maxJolts = adapters.get(adapters.size()-1);
        long[] R = new long[maxJolts + 1];
        R[0] = 1;
        R[1] = adapters.indexOf(1) != -1 ? R[0] : 0;
        R[2] = adapters.indexOf(2) != -1 ? R[1]+R[0] : 0;

        for(int i = 3 ; i <= maxJolts ; i++) {
            R[i] = adapters.indexOf(i) != -1 ? R[i-1] + R[i-2] + R[i-3] : 0;
        }

        return R[maxJolts];
    }

    @SneakyThrows
    private List<Integer> loadAdapters() {
        Path path = Paths.get(Day10AdapterArray.class.getResource("Day10AdapterArray_input.data").toURI());
        return Files.readAllLines(path)
                .stream()
                .map(number -> Integer.parseInt(number))
                .collect(Collectors.toList());
    }
}
