/*
 *    Copyright (c) 2018-2025, motionplus All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: motionplus
 */

package com.sixsixsix516.common.exception;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.sixsixsix516.common.utils.EmailUtil;
import com.sixsixsix516.common.utils.EnvUtil;
import com.sixsixsix516.common.utils.RequestUtil;
import com.sixsixsix516.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalBizExceptionHandler {

    /**
     * 全局异常捕获
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<Void> handleGlobalException(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        sendException(e);
        return Result.failed();
    }

    public static void sendException(Exception e) {
        if (EnvUtil.isDev()) {
            // 本地不发邮件
            return;
        }

        try {
            // 邮件发送人
            List<String> sendUserList = getSendUserList();

            String url = "";
            String requestParam = "";
            String method = "";
            StringBuilder header = new StringBuilder();
            Object sql = null;

            HttpServletRequest request = RequestUtil.getRequest();
            if (request != null) {

                // 请求头
                Enumeration<String> headerNames = request.getHeaderNames();
                while (headerNames.hasMoreElements()) {
                    String headerName = headerNames.nextElement();
                    header.append(headerName).append(": ").append(request.getHeader(headerName)).append("<br>");
                }

                requestParam = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                method = request.getMethod();

                url = request.getRequestURL().toString();
                if (HttpMethod.GET.name().equals(method)) {
                    String queryString = request.getQueryString();
                    if (StringUtils.isNotBlank(queryString)) {
                        // GET请求打印请求参数
                        url = url + "?" + request.getQueryString();
                    }
                }

                sql = request.getAttribute("sql");
            }


            EmailUtil.sendQQEmail(sendUserList, "系统异常【" + EnvUtil.getEnv() + "】",
                    "<h3>报错URL</h3>" + method + " " + url + (StringUtils.isBlank(requestParam) ? "" :
                            "<h3>请求参数</h3> " + requestParam) +
                            "<h3>报错项目</h3> " + applicationName +
                            "<h3>报错用户</h3> " + null +
                            "<h3>报错原因</h3> " + e.getClass().getName() + "<br>" + e.getMessage() +
                            "<h3>源码堆栈</h3>" + Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).filter(stackTrace -> stackTrace.startsWith("com.sixsixsix516")).collect(Collectors.joining("<br>")) +
                            "<h3>SQL</h3> " + sql +
                            "<h3>完整堆栈</h3>" + Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.joining("<br>")) + "<h3>请求头</h3>" + header, true);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    private static List<String> getSendUserList() {
        List<String> sendUserList = new ArrayList<>();
        sendUserList.add("xxx@xxx.com");

        return sendUserList;
    }


    private static String applicationName;

    @Value("${spring.application.name}")
    public void setApplicationName(String applicationName) {
        GlobalBizExceptionHandler.applicationName = applicationName;
    }

}
