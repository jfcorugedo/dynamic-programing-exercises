package com.jfcorugedo.udemy.javascriptalgorithmsanddatastructures;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given two strings, write a function to determine if the second string is an anagram of the first.
 * An anagram is a word or phrase formed by rearranging the letters of another.
 *
 * Example:
 * cinema -> iceman
 */
public class AnagramString {

    @Test
    public void emptyStrings() {

        assertThat(solution("", "")).isTrue();
    }

    @Test
    public void nullInputs() {

        assertThat(solution(null, null)).isTrue();
    }

    @Test
    public void differentLength() {

        assertThat(solution("abc", "ab")).isFalse();
    }

    @Test
    public void sameString() {

        assertThat(solution("abc", "abc")).isTrue();
    }

    @Test
    public void notAnagram() {

        assertThat(solution("cinema", "icemam")).isFalse();
    }

    @Test
    public void anagramString() {

        assertThat(solution("cinema", "iceman")).isTrue();
    }

    private boolean solution(String first, String second) {

        if(first == second) return true;//true only if both are null
        if(first.equals(second)) return true;
        if(first.length() != second.length()) return false;

        char[] firstSorted = first.toCharArray();
        Arrays.sort(firstSorted);

        char[] secondSorted = second.toCharArray();
        Arrays.sort(secondSorted);

        boolean same = true;
        for (int i = 0; i < firstSorted.length && same ; i++) {
            same = firstSorted[i] == secondSorted[i];
        }

        return same;
    }
}
