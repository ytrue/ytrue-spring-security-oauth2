package com.ytrue.security.error;

import com.ytrue.util.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p> 认证url权限 - 登录后访问接口无权限 - 自定义403无权限响应内容 </p>
 *
 * @author : zhengqing
 * @description : 登录过后的权限处理 【注：要和未登录时的权限处理区分开哦~】
 * @date : 2019/10/14 18:52
 */
@Component
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
        log.info("权限不足");
        ResponseData.jsonOut(response,
                ResponseData.fail(403, "权限不足"));
    }

}
