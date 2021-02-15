package com.jfcorugedo.udemy.javascriptalgorithmsanddatastructures;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Write a function which takes in a string and returns counts of each character in the string
 *
 * For example:
 *
 * Given "hello"
 * Then the function will return:
 * - h: 1
 * - e: 1
 * - l: 2
 * - o: 1
 *
 */
public class CountCharacters {

    @Test
    public void countEmptyString() {

        Map<String, Integer> counters = solution("");
        assertThat(counters).isEmpty();
    }

    @Test
    public void countNullInput() {

        Map<String, Integer> counters = solution(null);
        assertThat(counters).isEmpty();
    }

    @Test
    public void countWithSpacesAtTheBoundaries() {

        Map<String, Integer> counters = solution("   a   ");
        assertThat(counters)
                .hasSize(1)
                .containsEntry("a", 1);
    }

    @Test
    public void countWithSpacesInBetween() {

        Map<String, Integer> counters = solution("a   a");
        assertThat(counters)
                .hasSize(1)
                .containsEntry("a", 2);
    }

    @Test
    public void countCharacters() {

        Map<String, Integer> counters = solution("hello");
        assertThat(counters)
                .containsKeys("h", "e", "l", "o")
                .containsEntry("h", 1)
                .containsEntry("e", 1)
                .containsEntry("l", 2)
                .containsEntry("o", 1);
    }

    @Test
    public void countWithUpperCases() {

        Map<String, Integer> counters = solution("Aa");
        assertThat(counters)
                .hasSize(1)
                .containsEntry("a", 2);
    }

    private Map<String, Integer> solution(String text) {

        if(text == null || text.trim().isEmpty()) return Collections.emptyMap();

        Map<String, Integer> counter = new HashMap<>();
        for (String s : text.replaceAll(" ", "").toLowerCase(Locale.ROOT).split("")) {
            counter.put(s, counter.computeIfAbsent(s, v -> 0) + 1);
        }
        return counter;
    }
}
