package com.sixsixsix516.user.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.sixsixsix516.common.vo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 限流 - 快速失败后进入~
 *
 * @author SUN
 * @date 2022/2/13
 */
@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        // 返回 JSON
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.println(JSON.toJSONString(new Result<Void>(500, "现在人太多了~ 请稍后再来~", null)));
        writer.flush();
        writer.close();
    }
}
