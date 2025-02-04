package com._2je7.pofol.Config;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com._2je7.pofol.Dto.Common.Response.CommonResponse;
import com._2je7.pofol.Enum.ResponseCode;

@RestControllerAdvice(basePackages = {"com._2je7.pofol.Controller","com._2je7.pofol.Config.JWT"})
public class ApiExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

	//모든 예외를 핸들링하여 ErrorResponse 형식으로 반환한다.
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<CommonResponse<Object>> handleException(Exception e) {
        logger.error("handleException", e);
        
        CommonResponse<Object> commonResponse = CommonResponse.builder()
				.code(ResponseCode.EXCEPTION_ERROR.value())
				.httpStatus(ResponseCode.EXCEPTION_ERROR)
				.message("서버로그 확인 필요")
				.result(e.toString())
				.count(-1)
				.build();
        
        return new ResponseEntity<>(commonResponse, HttpStatus.BAD_REQUEST);
    }
	/*
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("handleHttpRequestMethodNotSupportedException", e);

        final ErrorResponse response
                = ErrorResponse
                    .create()
                    .code(HttpStatus.METHOD_NOT_ALLOWED.value())
                    .httpStatus(HttpStatus.METHOD_NOT_ALLOWED)
                    .message(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }
 
    //@Valid 검증 실패 시 Catch
    @ExceptionHandler(InvalidParameterException.class)
    protected ResponseEntity<ErrorResponse> handleInvalidParameterException(InvalidParameterException e) {
        logger.error("handleInvalidParameterException", e);

        ErrorCode errorCode = e.getErrorCode();

        ErrorResponse response
                = ErrorResponse
                    .create()
                    .httpStatus(errorCode.getStatus())
                    .message(e.toString())
                    .errors(e.getErrors());

        return new ResponseEntity<>(response, errorCode.getStatus());
    }

    //CustomException을 상속받은 클래스가 예외를 발생 시킬 시, Catch하여 ErrorResponse를 반환한다.
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        logger.error("handleAllException", e);

        ErrorCode errorCode = e.getErrorCode();

        ErrorResponse response
                = ErrorResponse
                    .create()
                    .httpStatus(errorCode.getStatus())
                    .code(errorCode.getCode())
                    .message(e.toString());

        return new ResponseEntity<>(response, errorCode.getStatus());
    }
    
    //모든 예외를 핸들링하여 ErrorResponse 형식으로 반환한다.
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        logger.error("handleException", e);

        ErrorResponse response
                = ErrorResponse
                    .create()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(e.toString());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException e) {
    	logger.error("handleValidationExceptions", e);
    	
        ErrorResponse response
        		= ErrorResponse
	                .create()
	                .code(ErrorCode.INVALID_PARAMETER.getCode())
	                .httpStatus(ErrorCode.INVALID_PARAMETER.getStatus())
	                .message(e.toString())
	                .errors(e.getBindingResult());
        
        return new ResponseEntity<>(response, ErrorCode.INVALID_PARAMETER.getStatus());
    }
    
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> missingServletRequestParameterException(MissingServletRequestParameterException e) {
    	logger.error("missingServletRequestParameterException", e);
    	
    	ErrorResponse response
        	= ErrorResponse
                .create()
                .code(HttpStatus.BAD_REQUEST.value())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(e.getMessage());
    	

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> constraintViolationException(ConstraintViolationException e) {
    	logger.error("ConstraintViolationException", e);
    	
    	ErrorResponse response
        	= ErrorResponse
                .create()
                .code(HttpStatus.BAD_REQUEST.value())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(e.getMessage());
    	
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
    
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> CustomExpiredJwtException(ExpiredJwtException e) {
    	logger.error("ConstraintViolationException", e);
    	
    	ErrorResponse response
        	= ErrorResponse
                .create()
                .code(HttpStatus.UNAUTHORIZED.value())
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .message(e.getMessage());
    	
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	*/
}