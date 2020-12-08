package com.ytrue.util;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@Data
public class ResponseData<T> {

    private Integer code;

    private String message;

    private T data;

    public ResponseData() {
    }

    public ResponseData(T data) {
        this.data = data;
    }

    public ResponseData(Integer code, String msg, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseData<HashMap<String, Object>> list(int total, List<T> rows) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", rows);
        return success(map);
    }

    public static <T> ResponseData<T> success() {
        ResponseData<T> resp = new ResponseData<T>(null);
        //操作成功
        resp.setCode(200);
        resp.setMessage("success");
        return resp;
    }

    public static <T> ResponseData<T> success(T data) {
        ResponseData<T> resp = new ResponseData<T>(data);
        //操作成功
        resp.setCode(200);
        resp.setMessage("success");
        return resp;
    }

    public static <T> ResponseData<T> success(String message, T data) {
        ResponseData<T> resp = new ResponseData<T>(data);
        //操作成功
        resp.setCode(200);
        resp.setMessage(message);
        return resp;
    }

    public static <T> ResponseData<T> success(Integer code, String message, T data) {
        ResponseData<T> resp = new ResponseData<T>(data);
        //操作成功
        resp.setCode(code);
        resp.setMessage(message);
        return resp;
    }


    public static <T> ResponseData<T> fail() {
        ResponseData<T> resp = new ResponseData<T>(null);
        //操作失败
        resp.setCode(500);
        resp.setMessage("fail");
        return resp;
    }

    public static <T> ResponseData<T> fail(String message) {
        ResponseData<T> resp = new ResponseData<T>();
        //操作失败
        resp.setCode(500);
        resp.setMessage(message);
        return resp;
    }


    public static <T> ResponseData<T> fail(Integer code, String message) {
        ResponseData<T> resp = new ResponseData<T>();
        //操作失败
        resp.setCode(code);
        resp.setMessage(message);
        return resp;
    }

    public static <T> ResponseData<T> fail(Integer code, String message, T data) {
        ResponseData<T> resp = new ResponseData<T>(data);
        //操作失败
        resp.setCode(code);
        resp.setMessage(message);
        return resp;
    }


    /**
     * json
     * @param response
     * @param result
     */
    public static void jsonOut(HttpServletResponse response, ResponseData<Object> result) {
        PrintWriter out = null;
        try {
            //设置200，方便前端处理
            response.setStatus(200);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JSON.toJSONString(result));
        } catch (Exception e) {
            System.out.println(e + "输出JSON出错");
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
}
