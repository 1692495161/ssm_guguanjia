package com.cjj.controller;

import com.cjj.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cjj
 * @date 2020/7/17
 * @description
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    @ResponseBody
    public Result getException(Exception e){
        return new Result(false,e.getMessage(),null);
    }
}
