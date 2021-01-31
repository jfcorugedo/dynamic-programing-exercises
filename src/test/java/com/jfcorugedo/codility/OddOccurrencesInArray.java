package com.jfcorugedo.codility;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A non-empty array A consisting of N integers is given. The array contains an odd number of elements, and each element
 * of the array can be paired with another element that has the same value, except for one element that is left unpaired.
 *
 * For example, in array A such that:
 *
 *   A[0] = 9  A[1] = 3  A[2] = 9
 *   A[3] = 3  A[4] = 9  A[5] = 7
 *   A[6] = 9
 * the elements at indexes 0 and 2 have value 9,
 * the elements at indexes 1 and 3 have value 3,
 * the elements at indexes 4 and 6 have value 9,
 * the element at index 5 has value 7 and is unpaired.
 * Write a function:
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given an array A consisting of N integers fulfilling the above conditions, returns the value of the unpaired element.
 *
 * For example, given array A such that:
 *
 *   A[0] = 9  A[1] = 3  A[2] = 9
 *   A[3] = 3  A[4] = 9  A[5] = 7
 *   A[6] = 9
 * the function should return 7, as explained in the example above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an odd integer within the range [1..1,000,000];
 * each element of array A is an integer within the range [1..1,000,000,000];
 * all but one of the values in A occur an even number of times.
 */
public class OddOccurrencesInArray {

    @Test
    public void findUnpaired() {

        assertThat(solution(new int[]{9, 3, 9, 3, 9, 7, 9})).isEqualTo(7);
        assertThat(
                solution(
                        Stream.concat(
                                Stream.concat(
                                        IntStream.range(1,40000).mapToObj(Integer::valueOf),
                                        Stream.of(Integer.valueOf(54400))
                                ),
                                IntStream.range(1,40000).mapToObj(Integer::valueOf)
                        ).mapToInt(Integer::intValue).toArray()
                )
        ).isEqualTo(54400);
    }

    public int solution(int[] samples) {

        List<Integer> sortedSamples = Arrays.stream(samples).sorted().mapToObj(Integer::valueOf).collect(Collectors.toList());

        int unpaired = -1;
        for(int i = 1 ; i < sortedSamples.size() && unpaired == -1 ; i++) {
            if(i == sortedSamples.size()-1) {
                unpaired = sortedSamples.get(i);
            } else {
                int current = sortedSamples.get(i);
                if (current != sortedSamples.get(i - 1) && current != sortedSamples.get(i + 1)) {
                    unpaired = current;
                }
            }
        }

        return unpaired;
    }

    @Test
    public void findUnpaired2() {

        assertThat(solution2(new int[]{9, 3, 9, 3, 9, 7, 9})).isEqualTo(7);
    }

    public int solution2(int[] A) {

        int unpaired = 0;
        for(int i = 0 ; i < A.length ; i++) {
            unpaired ^= A[i];
        }

        return unpaired;
    }
}
