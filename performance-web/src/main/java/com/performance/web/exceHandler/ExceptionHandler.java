package com.performance.web.exceHandler;

import com.alibaba.fastjson.JSON;
import com.performance.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * spring-mvc异常处理拦截器
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver{

    private static final Logger _logger = LoggerFactory.getLogger(ExceptionHandler.class);
    private static final String X_REQUESTED_WITH_HEADER = "x-Requested-With";
    private static final String X_REQUESTED_WITH = "XMLHttpRequest";
    private static final String APP_CONTEXT_JSON_CHARSET_UTF8 = "application/json;charset=utf-8";

    @Override
    public ModelAndView resolveException(HttpServletRequest request
            , HttpServletResponse response, Object o, Exception e) {
        _logger.error(new StringBuilder("系统访问路径：")
                .append(request.getRequestURI())
                .append(",参数：")
                .append(JSON.toJSONString(request.getParameterMap()))
                .append("出现异常：")
                .append(e.getMessage())
                .toString()
                , e);
        String xRequestedWith = request.getHeader(X_REQUESTED_WITH_HEADER);
        if(xRequestedWith != null && xRequestedWith.equals(X_REQUESTED_WITH)){
            Result result = new Result(false, e.getMessage());
            response.setContentType(APP_CONTEXT_JSON_CHARSET_UTF8);
            response.setStatus(HttpStatus.OK.value());
            try {
                PrintWriter out = response.getWriter();
                out.println(JSON.toJSONString(result));
                out.flush();
                out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else{
            // PROBLEM 测试乱码
            Map<String, String> map = new HashMap<>();
            map.put("errMsg", e.getMessage());
            return new ModelAndView("/handler/error", map);
        }
        return null;
    }
}
