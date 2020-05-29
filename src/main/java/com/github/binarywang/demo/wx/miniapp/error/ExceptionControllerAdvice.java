package com.github.binarywang.demo.wx.miniapp.error;

import com.github.binarywang.demo.wx.miniapp.vo.RestExStatus;
import com.github.binarywang.demo.wx.miniapp.vo.RestResult;
import com.github.binarywang.demo.wx.miniapp.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import javax.xml.bind.ValidationException;

@ControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    public ExceptionControllerAdvice() {
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handle(MissingServletRequestParameterException e) {
        log.error(e.getMessage(), e);
        Result result = Result.of(RestExStatus.MISSING_PARAMETERS);
        //result.setRequestId(this.getRequestId());
        return result;
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handle(HttpMessageNotReadableException e) {
        log.error(e.getMessage(), e);
        Result result = Result.of(RestExStatus.PARSING_PARAMETERS);
        //result.setRequestId(this.getRequestId());
        return result;
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handle(IllegalArgumentException e) {
        log.error(e.getMessage(), e);
        Result result = Result.of(RestExStatus.IRREGULAR_PARAMETERS);
        //result.setRequestId(this.getRequestId());
        return result;
    }

    @ExceptionHandler({BindException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handle(BindException e) {
        log.error(e.getMessage(), e);
        Result result = Result.of(RestExStatus.BIND_PARAMETERS);
        //result.setRequestId(this.getRequestId());
        return result;
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handle(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        Result result = Result.of(RestExStatus.BIND_PARAMETERS);
        //result.setRequestId(this.getRequestId());
        return result;
    }

    @ExceptionHandler({ValidationException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handle(ValidationException exception) {
        log.error(exception.getMessage(), exception);
        Result result = Result.of(RestExStatus.VALIDATION_PARAMETERS);
        //result.setRequestId(this.getRequestId());
        return result;
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handle(NoHandlerFoundException e) {
        log.error(e.getMessage(), e);
        Result result = Result.of(RestExStatus.NOT_FOUNT);
        //result.setRequestId(this.getRequestId());
        return result;
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Result handle(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        Result result = Result.of(RestExStatus.UN_SUPPORT_METHOD);
        //result.setRequestId(this.getRequestId());
        return result;
    }

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public Result handle(HttpMediaTypeNotSupportedException e) {
        log.error(e.getMessage(), e);
        Result result = Result.of(RestExStatus.UN_SUPPORT_MEDIA);
        //result.setRequestId(this.getRequestId());
        return result;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RestResult<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException( MethodArgumentNotValidException e ) ", e);
        RestResult<Object> restResult = new RestResult();
        restResult.setCode("500");
        restResult.setMsg("系统异常");
        restResult.setSucceed(false);
        //restResult.setRequestId(this.getRequestId());
        return restResult;
    }


    @ExceptionHandler({BaseRuntimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RestResult handleBaseRuntimeException(BaseRuntimeException e) {
        log.error("handleBaseRuntimeException->", e);
        RestResult restResult = new RestResult();
        restResult.setCode(e.getErrorCode());
        restResult.setMsg(e.getMessage());
        restResult.setSucceed(false);
        // restResult.setRequestId(this.getRequestId());
        return restResult;
    }


    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RestResult handleException(Exception e) {
        log.error("unknown Exception handler ->", e);
        RestResult restResult = new RestResult();
        restResult.setSucceed(false);
        restResult.setMsg("系统错误！");
        restResult.setCode("500");
        //restResult.setRequestId(this.getRequestId());
        return restResult;
    }

//        private String getRequestId() {
//            HttpServletRequest currentRequest = Global.getCurrentRequest();
//            return currentRequest != null ? currentRequest.getHeader("x-request-id") : null;
//        }
}
