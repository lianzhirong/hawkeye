package com.hawkeye.common.error;


import com.hawkeye.util.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ResponseBean bizExceptionHandler(HttpServletRequest req, BizException e, HttpServletResponse response) {
        log.error("发生业务异常！原因是：{}", e.getErrorMsg());
        response.setStatus(CommonCodeEnum.INTERNAL_SERVER_ERROR.getResultCode());
        return ResponseBean.error(e.getErrorCode(), e.getErrorMsg());
    }



    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseBean exceptionHandler(HttpServletRequest req, Exception e, HttpServletResponse response) {
        if (e instanceof ConstraintViolationException) {
            String message = "";
            Set<javax.validation.ConstraintViolation<?>> set = ((ConstraintViolationException) e).getConstraintViolations();
            for (javax.validation.ConstraintViolation<?> s : set) {
                message = s.getMessageTemplate();
                break;
            }
            log.error(" GlobalExceptionHandler exceptionHandler 参数异常:", e);
            response.setStatus(408);
            return ResponseBean.error(message);
        } else {
            log.error("未知异常！原因是:", e);
            response.setStatus(CommonCodeEnum.INTERNAL_SERVER_ERROR.getResultCode());
            return ResponseBean.error(CommonCodeEnum.INTERNAL_SERVER_ERROR);
        }

    }


    /**
     * 处理ClassNotFound异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = ClassNotFoundException.class)
    @ResponseBody
    public ResponseBean classNotFoundEceptionHandler(HttpServletRequest req, ClassNotFoundException e, HttpServletResponse response) {
        log.error("未知异常！原因是:", e);
        response.setStatus(CommonCodeEnum.INTERNAL_SERVER_ERROR.getResultCode());
        return ResponseBean.error(CommonCodeEnum.NOT_FOUND);
    }
}

