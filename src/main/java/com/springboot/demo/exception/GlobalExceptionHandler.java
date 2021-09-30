package com.springboot.demo.exception;

import com.springboot.demo.common.result.Rs;
import com.springboot.demo.common.result.RsStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
@Component
public class GlobalExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //参数校验,method params
    @ExceptionHandler({ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object validationException(ValidationException ex){
        ConstraintViolationException errors = (ConstraintViolationException)ex;
        Set<ConstraintViolation<?>> alidationvs = errors.getConstraintViolations();
        List<String> list = new ArrayList<>();
        alidationvs.forEach(item->{
            list.add(item.getMessage());
        });
        logger.error(ex.getMessage(),ex);
        return Rs.error(RsStatus.BAD_REQUEST).setMsg(list.toString());
    }

    //参数校验
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object methodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        List<String> list = new ArrayList<>();
        errors.forEach(item->{
            list.add(item.getDefaultMessage());
        });
        logger.error(ex.getMessage(),ex);
        return Rs.error(RsStatus.BAD_REQUEST).setMsg(list.toString());
    }

    //未传入json参数，request body
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object httpMessageNotReadableException(HttpMessageNotReadableException ex){
        logger.error(ex.getMessage(),ex);
        return Rs.error(RsStatus.BAD_REQUEST);
    }

    //method not allowed
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Object httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
        logger.error(ex.getMessage(),ex);
        return Rs.error(RsStatus.METHOD_NOT_ALLOWED);
    }

    //data not exist
    @ExceptionHandler({DataNotExistException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object dataNotExistException(DataNotExistException ex){
        logger.error(ex.getMessage(),ex);
        return Rs.error(RsStatus.DATA_NOT_EXIST).setMsg(ex.getLocalizedMessage());
    }

    //data exist
    @ExceptionHandler({DataExistException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object dataExistException(DataExistException ex){
        logger.error(ex.getMessage(),ex);
        return Rs.error(RsStatus.DATA_EXIST).setMsg(ex.getLocalizedMessage());
    }

    //other exception
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object CredentialsExpiredException(Exception ex){
        logger.error(ex.getMessage(),ex);
        return Rs.error(RsStatus.ERROR);
    }

}
