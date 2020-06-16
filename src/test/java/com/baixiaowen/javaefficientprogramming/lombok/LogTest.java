package com.baixiaowen.javaefficientprogramming.lombok;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @Slf4j
 */
@Slf4j
public class LogTest {

    @Test
    public void func(){
        log.error("日志！！！");
    }
}