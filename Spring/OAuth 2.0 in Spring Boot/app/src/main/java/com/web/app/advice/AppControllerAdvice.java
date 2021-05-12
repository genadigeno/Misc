package com.web.app.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.net.ConnectException;

@ControllerAdvice
public class AppControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(AppControllerAdvice.class);

    @ExceptionHandler(value = ConnectException.class)
    public String handle(ConnectException e, Model model){
        log.error(e.getMessage());
        model.addAttribute("message", e.getMessage());
        return "internal-server-error";
    }
}
