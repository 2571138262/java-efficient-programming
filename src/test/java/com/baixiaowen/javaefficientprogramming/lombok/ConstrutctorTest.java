package com.baixiaowen.javaefficientprogramming.lombok;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @AllArgsConstructor
 * @NoArgsConstructor
 * @RequiredArgsConstructor
 */
//@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
public class ConstrutctorTest {

    private final String field1;

    @NonNull
    private String field2;

    private String field3;
}
