package com.ytrue.exeption;


import com.ytrue.util.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

/**
 * @author yangyi
 * @date 2020/6/16 15:52
 * @description：捕获特定的异常
 */
@ControllerAdvice
@Slf4j
public class OtherException {

    /**
     * 捕获自定义验证的异常
     *
     * @param exception
     * @param response
     * @return
     */
    @ExceptionHandler({CustomException.class})
    public Object handleArithmeticException(
            CustomException exception,
            HttpServletResponse response
    ) {
        ResponseData.jsonOut(response, ResponseData.fail(500, exception.getMessage()));
        return null;
    }
}
