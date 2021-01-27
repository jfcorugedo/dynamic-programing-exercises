package com.jfcorugedo;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ReducerTest {

    @Test
    public void reduce() {

        assertThat(
                Arrays.stream("012345678".split(""))
                        .reduce( (acc, cur) -> Integer.parseInt(acc) > Integer.parseInt(cur) ? acc : cur)
                        .get()
        ).isEqualTo("8");
    }

    @Test
    public void reduce2() {

        assertThat(
                Arrays.stream("012345678".split(""))
                        .reduce("0", (acc, cur) -> Integer.parseInt(acc) > Integer.parseInt(cur) ? acc : cur)
        ).isEqualTo("8");
    }

    @Test
    public void reduce3() {

        assertThat(
                Arrays.stream("012345678".split(""))
                        .reduce(
                                new Counter(0),
                                (acc, cur) -> acc.getValue() > Integer.parseInt(cur) ? acc : new Counter(Integer.parseInt(cur)),
                                (a, b) -> a.getValue() > b.getValue() ? a : b
                        ).getValue()
        ).isEqualTo(8);
    }

    class Counter {
        private int value;

        Counter(int value) {this.value = value;}

        public int getValue() {return value;}
        public Counter setValue() {this.value = value; return this;}
    }
}
