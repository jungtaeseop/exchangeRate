package com.exchange.exchangerate.exception;

import com.sun.jdi.InternalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;


@Slf4j
@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public String exchangeRateUserException(Exception e, Model model) {
        log.warn("Exception: "+e.getMessage());
        model.addAttribute("error",e.getMessage());
        return "/exchangeRate :: #resultDiv";
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InternalException.class)
    public String exchangeRateInternalException(Exception e, Model model) {
        log.warn("Exception: "+e.getMessage());
        model.addAttribute("error",e.getMessage());
        return "/exchangeRate :: #resultRate";
    }
}

