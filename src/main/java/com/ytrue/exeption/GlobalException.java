package com.ytrue.exeption;


import com.ytrue.util.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author yangyi
 * @date 2020/6/15 16:27
 * @description：全局异常处理类
 */
@Controller
@Slf4j
public class GlobalException implements ErrorController {

    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = {"/error"})
    @ResponseBody
    public Object error(HttpServletResponse response, WebRequest req) {
        //设置200，方便前端处理
        response.setStatus(200);
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(req, false);
        //返回错误
        return ResponseData.
                fail((Integer) errorAttributes.get("status"),
                        errorAttributes.get("error") + "：" + errorAttributes.get("message"));
    }
}
