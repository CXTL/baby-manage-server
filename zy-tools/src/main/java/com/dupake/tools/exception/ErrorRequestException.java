/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package com.dupake.tools.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @author dupake
 * @date 2019-11-11
 * 统一异常处理
 */
@Getter
public class ErrorRequestException extends RuntimeException{

    private Integer status = BAD_REQUEST.value();

    public ErrorRequestException(String msg){
        super(msg);
    }

    public ErrorRequestException(HttpStatus status, String msg){
        super(msg);
        this.status = status.value();
    }
}
