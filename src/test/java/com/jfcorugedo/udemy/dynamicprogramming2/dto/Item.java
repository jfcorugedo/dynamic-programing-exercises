package com.jfcorugedo.udemy.dynamicprogramming2.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Item {
    private int weight;
    private int value;
}
