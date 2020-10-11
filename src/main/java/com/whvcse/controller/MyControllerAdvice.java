package com.whvcse.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author 彭高浛
 * 2020/6/5/13:49
 */
@ControllerAdvice
public class MyControllerAdvice {
    @ExceptionHandler(AuthorizationException.class)
    public String unauth(AuthorizationException e) {
      /*  e.printStackTrace();*/
        return "unauth";
    }
}
