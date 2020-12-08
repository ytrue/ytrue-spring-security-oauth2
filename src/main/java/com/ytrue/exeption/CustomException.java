package com.ytrue.exeption;

/**
 * @author yangyi
 * @date 2020/6/16 15:59
 * @description：
 */
public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }
}
