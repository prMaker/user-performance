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
        /*PROBLEM request.getParameterMap 获取的数据*/
        _logger.error("系统访问路径：" + request.getRequestURI() + ",参数：" + request.getParameterMap()
                + "出现异常：" + e.getMessage(), e);
        String xRequestedWith = request.getHeader(X_REQUESTED_WITH_HEADER);
        if(xRequestedWith != null && xRequestedWith.equals(X_REQUESTED_WITH)){
            Result result = new Result(false, e.getMessage());
            // TODO 测试乱码
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
            Map<String, String> map = new HashMap<>();
            map.put("errMsg", e.getMessage());
            return new ModelAndView("/handler/error", map);
        }
        return null;
    }
}
